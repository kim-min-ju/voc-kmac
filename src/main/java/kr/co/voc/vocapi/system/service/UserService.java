package kr.co.voc.vocapi.system.service;

import kr.co.voc.vocapi.common.util.MessageUtil;
import kr.co.voc.vocapi.coreframework.service.BaseService;
import kr.co.voc.vocapi.system.dto.CodeDto;
import kr.co.voc.vocapi.system.mapper.CodeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 코드 서비스 클래스
 * 
 * @ClassName CodeService.java
 * @Description 코드 서비스 클래스
 * @author mjkim
 * @since 2023. 3. 3.
 */
@Service
public class CodeService extends BaseService
{
    // 코드 조작 위한 DAO 객체
    @Autowired
    private CodeMapper mapper;

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
     * 코드 목록[콤보박스용] 조회
     *
     * @param param 콤보박스용 코드 조건 포함 객체
     * @return 결과 행 및 페이지 정보를 포함한 PaginatedResponse 객체
     */
    public List<CodeDto.Info> getCodeComboList(CodeDto.Request param)
    {
        return mapper.getCodeList(param);
    }

    /**
     * 코드 목록 조회
     * 
     * @param param 코드 검색 조건 포함 객체
     * @return 결과 행 및 페이지 정보를 포함한 PaginatedResponse 객체
     */
    public CodeDto.ListResponse getCodeList(CodeDto.Request param)
    {
        // offset 계산, -1인 경우 전체 반환
        int offset = param.getPageNumber() < 0 || param.getPageSize() == 0 ?
                -1:param.getPageNumber() * param.getPageSize();
        //long total = offset == -1 ? 0:mapper.getCodeListCount(param);
        long total = mapper.getCodeListCount(param);
        return CodeDto.ListResponse
                .<CodeDto.Info>builder()
                .pageNumber(param.getPageNumber())
                .pageSize(param.getPageSize())
                .totalCount(total)
                .list(mapper.getCodeList(param, offset, param.getPageSize()))
                .build();
	}

    /**
     * 코드 상세 조회
     * 
     * @param param 코드 pk
     * @return 코드 객체
     */
    public CodeDto.Info getCode(CodeDto.Request param)
    {
        return mapper.getCode(param);
    }
    
    /**
     * 코드 등록(insert)
     * 
     * @param param 등록할 코드 객체
     * @return 적용된 행 수
     */
    @Transactional
    public CodeDto.Response insertCode(CodeDto.Info param) {
        CodeDto.Response res = CodeDto.Response.builder().build();

        res.setRtnCnt(mapper.insertCode(param));
        res.setRtnMessage(MessageUtil.getMessage("voc.save.0000"));
        return res;
    }

    /**
     * 코드 수정(update)
     *
     * @param param 저장할 코드 객체
     * @return 적용된 행 수
     */
    @Transactional
    public CodeDto.Response updateCode(CodeDto.Info param) {
        CodeDto.Response res = CodeDto.Response.builder().build();

        res.setRtnCnt(mapper.updateCode(param));
        res.setRtnMessage(MessageUtil.getMessage("cust.save.0000"));
        return res;
    }

	
    /**
     * 코드 삭제
     * 
     * @param param 삭제 할 조건객체
     * @return 적용된 행 수
     */
    @Transactional
    public CodeDto.Response deleteCode(CodeDto.Request param) {
        CodeDto.Response res = CodeDto.Response.builder().build();

        res.setRtnCnt(mapper.deleteCode(param));
        res.setRtnMessage(MessageUtil.getMessage("cust.delete.0000"));
        return res;
    }

}
