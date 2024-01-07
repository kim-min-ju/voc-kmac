package kr.co.kmac.system.service;

import kr.co.kmac.common.util.MessageUtil;
import kr.co.kmac.coreframework.common.exception.ApiBusinessException;
import kr.co.kmac.coreframework.service.BaseService;
import kr.co.kmac.system.dto.CustomCodeDto;
import kr.co.kmac.system.dto.CustomCodeMstDto;
import kr.co.kmac.system.mapper.CustomCodeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 회사지정코드 서비스 클래스
 * 
 * @ClassName CustomCodeService.java
 * @Description 회사지정코드 서비스 클래스
 * @customor mjkim
 * @since 2023. 9. 18.
 */
@Service
public class CustomCodeService extends BaseService
{
    // 회사지정코드 조작 위한 DAO 객체
    @Autowired
    private CustomCodeMapper mapper;

    // 메시지 소스
    @Autowired
    private MessageSource messageSource;

    /**
     * 메시지 소스 반환
     * 
     * @return 메시지 소스 객체
     */
    protected MessageSource getMessageSource()
    {
        return this.messageSource;
    }

    /**
     * 회사지정코드마스터 목록 조회
     * 
     * @param param 회사지정코드마스터 검색 조건 포함 객체
     * @return 결과 행 및 페이지 정보를 포함한 PaginatedResponse 객체
     */
    public CustomCodeMstDto.ListResponse getCustomCodeMstList(CustomCodeMstDto.Request param)
    {
        long total = mapper.getCustomCodeMstListCount(param);
        return CustomCodeMstDto.ListResponse
                .<CustomCodeMstDto.Info>builder()
                .totalCount(total)
                .list(mapper.getCustomCodeMstList(param))
                .build();
	}

    /**
     * 회사지정코드 목록 조회
     *
     * @param param 회사지정코드 검색 조건 포함 객체
     * @return 결과 행 및 페이지 정보를 포함한 PaginatedResponse 객체
     */
    public CustomCodeDto.ListResponse getCustomCodeList(CustomCodeDto.Request param)
    {
        long total = mapper.getCustomCodeListCount(param);
        return CustomCodeDto.ListResponse
                .<CustomCodeMstDto.Info>builder()
                .totalCount(total)
                .list(mapper.getCustomCodeList(param))
                .build();
    }

    /**
     * 회사지정코드 dropbox용 목록 조회
     *
     * @param param 회사지정코드 검색 조건 포함 객체
     * @return 결과 행 및 페이지 정보를 포함한 PaginatedResponse 객체
     */
    public CustomCodeDto.ListResponse getCustomCodeCodeList(CustomCodeDto.Request param)
    {
        return CustomCodeDto.ListResponse
                .<CustomCodeMstDto.Info>builder()
                .list(mapper.getCustomCodeCodeList(param))
                .build();
    }

    /**
     * 회사지정코드마스터 상세 조회
     * 
     * @param customMstSeq 회사지정코드마스터 pk
     * @return 회사지정코드마스터 객체
     */
    public CustomCodeMstDto.Info getCustomCodeMst(int customMstSeq)
    {
        return mapper.getCustomCodeMst(customMstSeq);
    }

    /**
     * 회사지정코드 상세 조회
     *
     * @param customSeq 회사지정코드 pk
     * @return 회사지정코드 객체
     */
    public CustomCodeDto.Info getCustomCode(int customSeq)
    {
        return mapper.getCustomCode(customSeq);
    }
    
    /**
     * 회사지정코드마스터 등록(insert)
     * 
     * @param param 등록할 회사지정코드마스터 객체
     * @return 적용된 행 수
     */
    @Transactional
    public CustomCodeMstDto.Response insertCustomCodeMst(CustomCodeMstDto.Info param) {
        CustomCodeMstDto.Response res = CustomCodeMstDto.Response.builder().build();

        CustomCodeMstDto.Request req = CustomCodeMstDto.Request.builder().build();
        req.setCustomType(param.getCustomType());
        req.setCustomGrpCd(param.getCustomGrpCd());
        if(mapper.getCustomCodeMstListCount(req) > 0) {
            throw new ApiBusinessException("error.9200","해당 코드가 이미 존재합니다.");
        }

        res.setRtnCnt(mapper.insertCustomCodeMst(param));
        res.setRtnKey(param.getReturnKey());    //pk
        res.setRtnMessage(MessageUtil.getMessage("common.insert.0000"));
        return res;
    }

    /**
     * 회사지정코드 등록(insert)
     *
     * @param param 등록할 회사지정코드 객체
     * @return 적용된 행 수
     */
    @Transactional
    public CustomCodeDto.Response insertCustomCode(CustomCodeDto.Info param) {
        CustomCodeDto.Response res = CustomCodeDto.Response.builder().build();

        CustomCodeDto.Info req = CustomCodeDto.Info.builder().build();
        req.setCustomType(param.getCustomType());
        req.setCustomMstSeq(param.getCustomMstSeq());
        req.setCustomCd(param.getCustomCd());
        if(mapper.getCustomCodeDup(req) > 0) {
            throw new ApiBusinessException("error.9200","해당 코드가 이미 존재합니다. 다른 코드를 입력해 주십시오");
        }

        res.setRtnCnt(mapper.insertCustomCode(param));
        res.setRtnMessage(MessageUtil.getMessage("common.insert.0000"));
        return res;
    }

    /**
     * 회사지정코드 배치등록(insert)
     *
     * @param list 등록할 회사지정코드 객체
     * @return 적용된 행 수
     */
    @Transactional
    public CustomCodeDto.Response batchCustomCode(List<CustomCodeDto.Info> list) {
        CustomCodeDto.Response res = CustomCodeDto.Response.builder().build();
        int rtnCnt = 0;

        for(CustomCodeDto.Info data : list) {
            // 중복 삭제 후 저장
            mapper.deleteCustomCodeDup(data);
            rtnCnt += mapper.insertCustomCode(data);
        }
        res.setRtnCnt(rtnCnt);
        res.setRtnMessage(MessageUtil.getMessage("common.insert.0000"));
        return res;
    }

    /**
     * 회사지정코드마스터 수정(update)
     *
     * @param param 저장할 회사지정코드마스터 객체
     * @return 적용된 행 수
     */
    @Transactional
    public CustomCodeMstDto.Response updateCustomCodeMst(CustomCodeMstDto.Info param) {
        CustomCodeMstDto.Response res = CustomCodeMstDto.Response.builder().build();

        res.setRtnCnt(mapper.updateCustomCodeMst(param));
        res.setRtnKey(param.getCustomMstSeq());    //pk
        res.setRtnMessage(MessageUtil.getMessage("common.update.0000"));
        return res;
    }

    /**
     * 회사지정코드 수정(update)
     *
     * @param param 저장할 회사지정코드 객체
     * @return 적용된 행 수
     */
    @Transactional
    public CustomCodeDto.Response updateCustomCode(CustomCodeDto.Info param) {
        CustomCodeDto.Response res = CustomCodeDto.Response.builder().build();

        res.setRtnCnt(mapper.updateCustomCode(param));
        res.setRtnMessage(MessageUtil.getMessage("common.update.0000"));
        return res;
    }

    /**
     * 회사지정코드마스터 삭제
     * 
     * @param customMstSeq 회사지정코드마스터 pk
     * @return 적용된 행 수
     */
    @Transactional
    public CustomCodeMstDto.Response deleteCustomCodeMst(int customMstSeq) {
        CustomCodeMstDto.Response res = CustomCodeMstDto.Response.builder().build();

        res.setRtnCnt(mapper.deleteCustomCodeMst(customMstSeq));
        res.setRtnMessage(MessageUtil.getMessage("common.delete.0000"));
        return res;
    }

    /**
     * 회사지정코드 삭제
     *
     * @param customSeq 회사지정코드 pk
     * @return 적용된 행 수
     */
    @Transactional
    public CustomCodeDto.Response deleteCustomCode(int customSeq) {
        CustomCodeDto.Response res = CustomCodeDto.Response.builder().build();

        res.setRtnCnt(mapper.deleteCustomCode(customSeq));
        res.setRtnMessage(MessageUtil.getMessage("common.delete.0000"));
        return res;
    }
}
