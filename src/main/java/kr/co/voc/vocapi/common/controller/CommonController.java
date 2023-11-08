package kr.co.voc.vocapi.common.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.voc.vocapi.common.util.ResponseUtil;
import kr.co.voc.vocapi.coreframework.common.response.ResponseObject;
import kr.co.voc.vocapi.coreframework.controller.BaseController;
import kr.co.voc.vocapi.voc.service.AttachFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 첨부파일 관리/작업 Controller
 * 
 * @ClassName AttachFileController.java
 * @Description 첨부파일
 * @author mjkim
 * @since 2023. 3. 3.
 */
@Tag(name = "AttachFileController", description = "첨부파일 관리 API")
@RestController
@RequestMapping("/vocapi/v1/file")
public class AttachFileController extends BaseController
{
    @Autowired
    private AttachFileService service;

    /**
     * 첨부파일 삭제
     *
     * @param fileSeq 삭제할 첨부파일 객체
     */
    @Operation(summary = "첨부파일 삭제", description = "첨부파일 정보를 삭제한다.")
    @PostMapping("/delete/{fileSeq}")
    public ResponseObject deleteAttachFile(HttpServletRequest req
            , @Parameter(description = "파일번호") @PathVariable(name = "fileSeq") int fileSeq)
    {
        return ResponseUtil.getResponse(req, service.deleteAttachFile(fileSeq));
    }
}
