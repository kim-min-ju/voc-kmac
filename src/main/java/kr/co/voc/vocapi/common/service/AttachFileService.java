package kr.co.voc.vocapi.voc.service;

import kr.co.voc.vocapi.common.util.MessageUtil;
import kr.co.voc.vocapi.coreframework.service.BaseService;
import kr.co.voc.vocapi.common.dto.AttachFileDto;
import kr.co.voc.vocapi.common.mapper.AttachFileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 첨부파일 서비스 클래스
 * 
 * @ClassName AttachFileService.java
 * @Description 첨부파일 서비스 클래스
 * @author mjkim
 * @since 2023. 3. 3.
 */
@Service
public class AttachFileService extends BaseService
{
    // 첨부파일 조작 위한 DAO 객체
    @Autowired
    private AttachFileMapper mapper;

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
     * VOC고객 삭제
     *
     * @param fileSeq 삭제 할 조건객체
     * @return 적용된 행 수
     */
    @Transactional
    public AttachFileDto.Response deleteAttachFile(int fileSeq) {
        AttachFileDto.Response res = AttachFileDto.Response.builder().build();

        res.setRtnCnt(mapper.deleteAttachFile(fileSeq));
        res.setRtnMessage(MessageUtil.getMessage("file.delete.0000"));
        return res;
    }

}
