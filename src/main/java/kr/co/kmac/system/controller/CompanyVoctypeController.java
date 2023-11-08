package kr.co.kmac.system.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.kmac.common.util.ResponseUtil;
import kr.co.kmac.common.util.validation.PostMethod;
import kr.co.kmac.coreframework.common.response.ResponseObject;
import kr.co.kmac.coreframework.controller.BaseController;
import kr.co.kmac.system.dto.VoctypeDto;
import kr.co.kmac.system.dto.VoctypeMstDto;
import kr.co.kmac.system.service.VoctypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * VOC유형 관리/작업 Controller
 * 
 * @ClassName VoctypeController.java
 * @Description VOC유형
 * @voctypeor mjkim
 * @since 2023. 9. 18.
 */
@Tag(name = "VoctypeController", description = "VOC유형관리 API")
@RestController
@RequestMapping("/kmacvoc/v1/voctype")
public class VoctypeController extends BaseController
{
    @Autowired
    private VoctypeService service;

    /**
     * VOC유형마스터 목록 조회
     * 
     * @param param VOC유형마스터 검색 조건 포함 객체
     * @return 결과 행 및 페이지 정보를 포함한 PaginatedResponse 객체
     */
    @Operation(summary = "GET VOC유형마스터 목록 조회", description = "VOC유형마스터 목록 조회")
    @GetMapping("/mst/list")
    public ResponseObject<VoctypeMstDto.ListResponse> getVoctypeMstList(HttpServletRequest req, VoctypeMstDto.Request param)
    {
        return ResponseUtil.getResponse(req, service.getVoctypeMstList(param));
    }

    /**
     * VOC유형 목록 조회
     *
     * @param param VOC유형 검색 조건 포함 객체
     * @return 결과 행 및 페이지 정보를 포함한 PaginatedResponse 객체
     */
    @Operation(summary = "GET VOC유형 목록 조회", description = "VOC유형 목록 조회")
    @GetMapping("/list")
    public ResponseObject<VoctypeDto.ListResponse> getVoctypeList(HttpServletRequest req, VoctypeDto.Request param)
    {
        return ResponseUtil.getResponse(req, service.getVoctypeList(param));
    }

    /**
     * VOC유형 dropbox용 목록 조회
     *
     * @param param VOC유형 검색 조건 포함 객체
     * @return 결과 행 및 페이지 정보를 포함한 PaginatedResponse 객체
     */
    @Operation(summary = "GET VOC유형 dropbox용 목록 조회", description = "VOC유형 dropbox용 목록 조회")
    @GetMapping("/code/list")
    public ResponseObject<VoctypeDto.ListResponse> getVoctypeCodeList(HttpServletRequest req, VoctypeDto.Request param)
    {
        return ResponseUtil.getResponse(req, service.getVoctypeCodeList(param));
    }

    /**
     * VOC유형마스터 상세 조회
     * 
     * @param voctypeMstSeq VOC유형마스터 검색 조건 포함 객체
     * @return VOC유형 객체
     */
    @Operation(summary = "GET VOC유형마스터 상세 조회", description = "voctypeMstSeq로 VOC유형마스터 정보 1건 조회")
    @GetMapping("/mst/{voctypeMstSeq}")
    public ResponseObject<VoctypeMstDto.Info> getVoctypeMst(HttpServletRequest req
            , @Parameter(description = "VOC유형마스터일련번호") @PathVariable(name = "voctypeMstSeq") int voctypeMstSeq)
    {
        return ResponseUtil.getResponse(req, service.getVoctypeMst(voctypeMstSeq));
    }

    /**
     * VOC유형 상세 조회
     *
     * @param voctypeSeq VOC유형 검색 조건 포함 객체
     * @return VOC유형 객체
     */
    @Operation(summary = "GET VOC유형 상세 조회", description = "voctypeSeq로 VOC유형 정보 1건 조회")
    @GetMapping("/{voctypeSeq}")
    public ResponseObject<VoctypeDto.Info> getVoctype(HttpServletRequest req
            , @Parameter(description = "VOC유형일련번호") @PathVariable(name = "voctypeSeq") int voctypeSeq)
    {
        return ResponseUtil.getResponse(req, service.getVoctype(voctypeSeq));
    }

    /**
     * VOC유형마스터 등록
     *ss
     * @param param 등록할 VOC유형마스터 객체
     * @return 등록결과 객체
     */
    @Operation(summary = "VOC유형마스터 등록", description = "VOC유형마스터 정보를 등록한다.")
    @PostMapping("/mst/add")
    public ResponseObject insertVoctypeMst(HttpServletRequest req, @RequestBody @Validated(PostMethod.class) VoctypeMstDto.Info param)
    {
        return ResponseUtil.getResponse(req, service.insertVoctypeMst(param));
    }

    /**
     * VOC유형 등록
     *ss
     * @param param 등록할 VOC유형 객체
     * @return 등록결과 객체
     */
    @Operation(summary = "VOC유형 등록", description = "VOC유형 정보를 등록한다.")
    @PostMapping("/add")
    public ResponseObject insertVoctype(HttpServletRequest req, @RequestBody @Validated(PostMethod.class) VoctypeDto.Info param)
    {
        return ResponseUtil.getResponse(req, service.insertVoctype(param));
    }

    /**
     * VOC유형마스터 수정
     *
     * @param param 수정할 VOC유형마스터 객체
     * @return 수정결과 객체
     */
    @Operation(summary = "VOC유형마스터 수정", description = "VOC유형마스터 정보를 수정한다.")
    @PostMapping("/mst/modify")
    public ResponseObject updateVoctypeMst(HttpServletRequest req, @RequestBody @Validated(PostMethod.class) VoctypeMstDto.Info param)
    {
        return ResponseUtil.getResponse(req, service.updateVoctypeMst(param));
    }

    /**
     * VOC유형 수정
     *
     * @param param 수정할 VOC유형 객체
     * @return 수정결과 객체
     */
    @Operation(summary = "VOC유형 수정", description = "VOC유형 정보를 수정한다.")
    @PostMapping("/modify")
    public ResponseObject updateVoctype(HttpServletRequest req, @RequestBody @Validated(PostMethod.class) VoctypeDto.Info param)
    {
        return ResponseUtil.getResponse(req, service.updateVoctype(param));
    }

    /**end
     * VOC유형마스터 삭제
     * 
     * @param voctypeMstSeq 삭제할 VOC유형마스터 key
     */
    @Operation(summary = "VOC유형마스터 삭제", description = "VOC유형마스터 삭제한다.")
    @PostMapping("/mst/remove/{voctypeMstSeq}")
    public ResponseObject deleteVoctypeMst(HttpServletRequest req
            , @Parameter(description = "VOC유형마스터일련번호") @PathVariable(name = "voctypeMstSeq") int voctypeMstSeq)
    {
        return ResponseUtil.getResponse(req, service.deleteVoctypeMst(voctypeMstSeq));
    }

    /**end
     * VOC유형 삭제
     *
     * @param voctypeSeq 삭제할 VOC유형 key
     */
    @Operation(summary = "VOC유형 삭제", description = "VOC유형정보를 삭제한다.")
    @PostMapping("/remove/{voctypeSeq}")
    public ResponseObject deleteVoctype(HttpServletRequest req
            , @Parameter(description = "VOC유형일련번호") @PathVariable(name = "voctypeSeq") int voctypeSeq)
    {
        return ResponseUtil.getResponse(req, service.deleteVoctype(voctypeSeq));
    }
}
