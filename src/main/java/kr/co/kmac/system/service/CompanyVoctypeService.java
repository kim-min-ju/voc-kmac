package kr.co.kmac.system.service;

import kr.co.kmac.common.util.MessageUtil;
import kr.co.kmac.coreframework.service.BaseService;
import kr.co.kmac.system.dto.VoctypeDto;
import kr.co.kmac.system.dto.VoctypeMstDto;
import kr.co.kmac.system.mapper.VoctypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * VOC유형 서비스 클래스
 * 
 * @ClassName VoctypeService.java
 * @Description VOC유형 서비스 클래스
 * @vocTypeor mjkim
 * @since 2023. 9. 18.
 */
@Service
public class VoctypeService extends BaseService
{
    // VOC유형 조작 위한 DAO 객체
    @Autowired
    private VoctypeMapper mapper;

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
     * VOC유형마스터 목록 조회
     * 
     * @param param VOC유형마스터 검색 조건 포함 객체
     * @return 결과 행 및 페이지 정보를 포함한 PaginatedResponse 객체
     */
    public VoctypeMstDto.ListResponse getVoctypeMstList(VoctypeMstDto.Request param)
    {
        long total = mapper.getVoctypeMstListCount(param);
        return VoctypeMstDto.ListResponse
                .<VoctypeMstDto.Info>builder()
                .totalCount(total)
                .list(mapper.getVoctypeMstList(param))
                .build();
	}

    /**
     * VOC유형 목록 조회
     *
     * @param param VOC유형 검색 조건 포함 객체
     * @return 결과 행 및 페이지 정보를 포함한 PaginatedResponse 객체
     */
    public VoctypeDto.ListResponse getVoctypeList(VoctypeDto.Request param)
    {
        long total = mapper.getVoctypeListCount(param);
        return VoctypeDto.ListResponse
                .<VoctypeMstDto.Info>builder()
                .totalCount(total)
                .list(mapper.getVoctypeList(param))
                .build();
    }

    /**
     * VOC유형 dropbox용 목록 조회
     *
     * @param param VOC유형 검색 조건 포함 객체
     * @return 결과 행 및 페이지 정보를 포함한 PaginatedResponse 객체
     */
    public VoctypeDto.ListResponse getVoctypeCodeList(VoctypeDto.Request param)
    {
        return VoctypeDto.ListResponse
                .<VoctypeMstDto.Info>builder()
                .list(mapper.getVoctypeCodeList(param))
                .build();
    }

    /**
     * VOC유형마스터 상세 조회
     * 
     * @param vocTypeMstSeq VOC유형마스터 pk
     * @return VOC유형마스터 객체
     */
    public VoctypeMstDto.Info getVoctypeMst(int vocTypeMstSeq)
    {
        return mapper.getVoctypeMst(vocTypeMstSeq);
    }

    /**
     * VOC유형 상세 조회
     *
     * @param vocTypeSeq VOC유형 pk
     * @return VOC유형 객체
     */
    public VoctypeDto.Info getVoctype(int vocTypeSeq)
    {
        return mapper.getVoctype(vocTypeSeq);
    }
    
    /**
     * VOC유형마스터 등록(insert)
     * 
     * @param param 등록할 VOC유형마스터 객체
     * @return 적용된 행 수
     */
    @Transactional
    public VoctypeMstDto.Response insertVoctypeMst(VoctypeMstDto.Info param) {
        VoctypeMstDto.Response res = VoctypeMstDto.Response.builder().build();

        res.setRtnCnt(mapper.insertVoctypeMst(param));
        res.setRtnMessage(MessageUtil.getMessage("common.insert.0000"));
        return res;
    }

    /**
     * VOC유형 등록(insert)
     *
     * @param param 등록할 VOC유형 객체
     * @return 적용된 행 수
     */
    @Transactional
    public VoctypeDto.Response insertVoctype(VoctypeDto.Info param) {
        VoctypeDto.Response res = VoctypeDto.Response.builder().build();

        res.setRtnCnt(mapper.insertVoctype(param));
        res.setRtnMessage(MessageUtil.getMessage("common.insert.0000"));
        return res;
    }

    /**
     * VOC유형마스터 수정(update)
     *
     * @param param 저장할 VOC유형마스터 객체
     * @return 적용된 행 수
     */
    @Transactional
    public VoctypeMstDto.Response updateVoctypeMst(VoctypeMstDto.Info param) {
        VoctypeMstDto.Response res = VoctypeMstDto.Response.builder().build();

        res.setRtnCnt(mapper.updateVoctypeMst(param));
        res.setRtnMessage(MessageUtil.getMessage("common.update.0000"));
        return res;
    }

    /**
     * VOC유형 수정(update)
     *
     * @param param 저장할 VOC유형 객체
     * @return 적용된 행 수
     */
    @Transactional
    public VoctypeDto.Response updateVoctype(VoctypeDto.Info param) {
        VoctypeDto.Response res = VoctypeDto.Response.builder().build();

        res.setRtnCnt(mapper.updateVoctype(param));
        res.setRtnMessage(MessageUtil.getMessage("common.update.0000"));
        return res;
    }

    /**
     * VOC유형마스터 삭제
     * 
     * @param vocTypeMstSeq VOC유형마스터 pk
     * @return 적용된 행 수
     */
    @Transactional
    public VoctypeMstDto.Response deleteVoctypeMst(int vocTypeMstSeq) {
        VoctypeMstDto.Response res = VoctypeMstDto.Response.builder().build();

        res.setRtnCnt(mapper.deleteVoctypeMst(vocTypeMstSeq));
        res.setRtnMessage(MessageUtil.getMessage("common.delete.0000"));
        return res;
    }

    /**
     * VOC유형 삭제
     *
     * @param vocTypeSeq VOC유형 pk
     * @return 적용된 행 수
     */
    @Transactional
    public VoctypeDto.Response deleteVoctype(int vocTypeSeq) {
        VoctypeDto.Response res = VoctypeDto.Response.builder().build();

        res.setRtnCnt(mapper.deleteVoctype(vocTypeSeq));
        res.setRtnMessage(MessageUtil.getMessage("common.delete.0000"));
        return res;
    }
}
