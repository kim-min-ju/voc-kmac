package kr.co.voc.vocapi.voc.service;

import kr.co.voc.vocapi.common.util.MessageUtil;
import kr.co.voc.vocapi.coreframework.common.exception.ApiBusinessException;
import kr.co.voc.vocapi.coreframework.service.BaseService;
import kr.co.voc.vocapi.voc.dto.VocMstDto;
import kr.co.voc.vocapi.voc.mapper.VocCustMapper;
import kr.co.voc.vocapi.voc.mapper.VocMstMapper;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * VOC마스터 서비스 클래스
 * 
 * @ClassName VocMstService.java
 * @Description VOC마스터 서비스 클래스
 * @author mjkim
 * @since 2023. 3. 3.
 */
@Service
public class VocMstService extends BaseService
{
    // VOC마스터 조작 위한 DAO 객체
    @Autowired
    private VocMstMapper mapper;
    @Autowired
    private VocCustMapper custMapper;

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
     * VOC마스터 목록 조회
     * 
     * @param param VOC마스터 검색 조건 포함 객체
     * @return 결과 행 및 페이지 정보를 포함한 PaginatedResponse 객체
     */
    public VocMstDto.ListResponse getVocMstList(VocMstDto.Request param)
    {
        // offset 계산, -1인 경우 전체 반환
        int offset = param.getPageNumber() < 0 || param.getPageSize() == 0 ?
                -1:param.getPageNumber() * param.getPageSize();
        //long total = offset == -1 ? 0:mapper.getVocMstListCount(param);
        long total = mapper.getVocMstListCount(param);
        return VocMstDto.ListResponse
                .<VocMstDto.Info>builder()
                .pageNumber(param.getPageNumber())
                .pageSize(param.getPageSize())
                .totalCount(total)
                .list(mapper.getVocMstList(param, offset, param.getPageSize()))
                .build();
	}

    /**
     * VOC마스터 상세 조회
     * 
     * @param vocSeq VOC마스터 pk
     * @return VOC마스터 객체
     */
    public VocMstDto.Info getVocMst(int vocSeq)
    {
        return mapper.getVocMst(vocSeq);
    }
    
    /**
     * VOC 접수(insert)
     * 
     * @param param 접수처리할 VOC마스터 객체
     * @return 적용된 행 수
     */
    @Transactional
    public VocMstDto.Response rcptVocMst(VocMstDto.Info param) {
        VocMstDto.Response res = VocMstDto.Response.builder().build();

        // 신규고객일 경우, 고객정보 등록
        if(ObjectUtils.isEmpty(param.getCustSeq())) {
            if(ObjectUtils.isEmpty(param.getVocCustInfo())) {
                throw new ApiBusinessException("error.1047", new String[] {"고객정보"});
            }
            custMapper.insertVocCust(param.getVocCustInfo());
            param.setCustSeq(param.getVocCustInfo().getReturnKey());
        }

        param.setVocStatusCd("P0"); // 처리중 상태

        // 즉시처리인 경우 처리항목 필수 체크
        if("Y".equals(param.getImmeActYn())) {
            if(ObjectUtils.isEmpty(param.getVocActTypeCd())) {
                throw new ApiBusinessException("error.1047", new String[] {"처리유형코드"});
            }

            if(ObjectUtils.isEmpty(param.getVocActDt())) {
                throw new ApiBusinessException("error.1047", new String[] {"처리일시"});
            }

            if(ObjectUtils.isEmpty(param.getVocActUserNo())) {
                throw new ApiBusinessException("error.1047", new String[] {"처리자"});
            }

            if(ObjectUtils.isEmpty(param.getVocActCont())) {
                throw new ApiBusinessException("error.1047", new String[] {"처리내용"});
            }

            param.setVocStatusCd("C0"); // 처리완료 상태
        }

        res.setRtnCnt(mapper.insertVocMst(param));
        res.setRtnMessage(MessageUtil.getMessage("voc.save.0000"));
        return res;
    }

    /**
     * VOC마스터 저장(update)
     *
     * @param param 저장할 VOC마스터 객체
     * @return 적용된 행 수
     */
    @Transactional
    public VocMstDto.Response saveVocMst(VocMstDto.Info param) {
        VocMstDto.Response res = VocMstDto.Response.builder().build();

        res.setRtnCnt(mapper.updateVocMst(param));
        return res;
    }

    /**
     * VOC 완료(update)
     *
     * @param param 완료처리할 VOC마스터 객체
     * @return 적용된 행 수
     */
    @Transactional
    public VocMstDto.Response finishVocMst(VocMstDto.Info param) {
        VocMstDto.Response res = VocMstDto.Response.builder().build();

        res.setRtnCnt(mapper.updateVocMst(param));
        return res;
    }

    /**
     * VOC 취소(update)
     *
     * @param param 저장 할 VOC마스터 객체
     * @return 적용된 행 수
     */
    @Transactional
    public VocMstDto.Response cancelVocMst(VocMstDto.Info param) {
        VocMstDto.Response res = VocMstDto.Response.builder().build();

        res.setRtnCnt(mapper.updateVocMst(param));
        return res;
    }

	
    /**
     * VOC마스터 삭제
     * 
     * @param param 삭제 할 조건객체
     * @return 적용된 행 수
     */
    @Transactional
    public VocMstDto.Response deleteVocMst(VocMstDto.Info param) {
        VocMstDto.Response res = VocMstDto.Response.builder().build();

        res.setRtnCnt(mapper.deleteVocMst(param));
        return res;
    }

}
