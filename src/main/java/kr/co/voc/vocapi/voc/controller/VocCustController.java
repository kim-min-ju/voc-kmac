package kr.co.voc.vocapi.voc.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.voc.vocapi.common.util.FileUtil;
import kr.co.voc.vocapi.common.util.ResponseUtil;
import kr.co.voc.vocapi.common.util.validation.PostMethod;
import kr.co.voc.vocapi.coreframework.common.response.ResponseObject;
import kr.co.voc.vocapi.coreframework.controller.BaseController;
import kr.co.voc.vocapi.voc.dto.AttachFileDto;
import kr.co.voc.vocapi.voc.dto.VocMstDto;
import kr.co.voc.vocapi.voc.service.VocMstService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * VOC마스터 관리/작업 Controller
 * 
 * @ClassName VocMstController.java
 * @Description VOC마스터
 * @author mjkim
 * @since 2023. 3. 3.
 */
@Tag(name = "VocMstController", description = "VOC 처리 API")
@RestController
@RequestMapping("/vocapi/v1/voc")
public class VocMstController extends BaseController
{
    @Autowired
    private VocMstService service;

    /**
     * VOC마스터 목록 조회
     * 
     * @param param VOC마스터 검색 조건 포함 객체
     * @return 결과 행 및 페이지 정보를 포함한 PaginatedResponse 객체
     */
    @Operation(summary = "GET VOC 목록 조회", description = "VOC정보 목록 조회")
    @GetMapping("/list")
    public ResponseObject<VocMstDto.ListResponse> getVocMstList(HttpServletRequest req, VocMstDto.Request param)
    {
        return ResponseUtil.getResponse(req, service.getVocMstList(param));
    }
	
    /**
     * VOC마스터 상세 조회
     * 
     * @param vocSeq VOC마스터 검색 조건 포함 객체
     * @return VOC마스터 객체
     */
    @Operation(summary = "GET VOC 상세 조회", description = "vocSeq로 VOC정보 1건 조회")
    @GetMapping("/{vocSeq}")
    public ResponseObject<VocMstDto.Info> getVocMst(HttpServletRequest req
            , @Parameter(description = "VOC일련번호") @PathVariable(name = "vocSeq") int vocSeq)
    {
        return ResponseUtil.getResponse(req, service.getVocMst(vocSeq));
    }

    /**
     * VOC 접수
     * 
     * @param param 저장 할 VOC마스터 객체
     * @return VOC접수결과 객체
     */
    @Operation(summary = "VOC 등록", description = "VOC정보를 등록한다.")
    @PostMapping("/rcpt")
    public ResponseObject rcptVocMst(MultipartHttpServletRequest req, @Validated(PostMethod.class) VocMstDto.Info param) throws Exception
    {
        _attachFile(param, req);
        return ResponseUtil.getResponse(req, service.rcptVocMst(param));
    }

    /**
     * VOC 저장
     *
     * @param param 저장 할 VOC마스터 객체
     * @return VOC저장결과 객체
     */
    @Operation(summary = "VOC 수정", description = "VOC정보를 수정한다.")
    @PostMapping("/save")
    public ResponseObject saveVocMst(MultipartHttpServletRequest req, @Validated(PostMethod.class) VocMstDto.Info param) throws Exception
    {
        _attachFile(param, req);
        return ResponseUtil.getResponse(req, service.saveVocMst(param));
    }

    /**
     * VOC 완료
     *
     * @param param 완료처리할 VOC마스터 객체
     * @return VOC완료결과 객체
     */
    @Operation(summary = "VOC 완료", description = "VOC정보를 완료처리한다.")
    @PostMapping("/finish")
    public ResponseObject finishVocMst(MultipartHttpServletRequest req, @Validated(PostMethod.class) VocMstDto.Info param) throws Exception
    {
        _attachFile(param, req);
        return ResponseUtil.getResponse(req, service.finishVocMst(param));
    }

    /**
     * VOC 취소
     *
     * @param param 취소처리할 VOC마스터 객체
     * @return VOC취소결과 객체
     */
    @Operation(summary = "VOC 취소", description = "VOC정보를 취소처리한다.")
    @PostMapping("/cancel")
    public ResponseObject cancelVocMst(HttpServletRequest req, @RequestBody VocMstDto.Info param)
    {
        return ResponseUtil.getResponse(req, service.cancelVocMst(param));
    }

    /**
     * VOC마스터 삭제
     * 
     * @param vocSeq 삭제할 VOC마스터 객체
     */
    @Operation(summary = "VOC 삭제", description = "VOC정보를 삭제한다.")
    @PostMapping("/delete/{vocSeq}")
    public ResponseObject deleteVocMst(HttpServletRequest req
            , @Parameter(description = "VOC일련번호") @PathVariable(name = "vocSeq") int vocSeq)
    {
        return ResponseUtil.getResponse(req, service.deleteVocMst(vocSeq));
    }

    /**
     * VOC 파일첨부 공통처리
     *
     * @param param 파일업로드 후 셋팅할 파일정보 객체
     * @param mRequest
     */
    private void _attachFile(VocMstDto.Info param, MultipartHttpServletRequest mRequest) throws Exception {
        List<AttachFileDto.Info> fileList = null;
        List<MultipartFile> files = mRequest.getFiles("files");
        if(files.size() > 0) {
            fileList = FileUtil.attachFile(files);
            param.setFileList(fileList);
        }
    }
}
