package kr.co.voc.vocapi.voc.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.voc.vocapi.common.util.ResponseUtil;
import kr.co.voc.vocapi.coreframework.common.response.ResponseObject;
import kr.co.voc.vocapi.coreframework.controller.BaseController;
import kr.co.voc.vocapi.voc.dto.VocCustDto;
import kr.co.voc.vocapi.voc.service.VocCustService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * VOC고객 관리/작업 Controller
 * 
 * @ClassName VocCustController.java
 * @Description VOC고객
 * @author mjkim
 * @since 2023. 3. 3.
 */
@Tag(name = "VocCustController", description = "VOC 고객관리 API")
@RestController
@RequestMapping("/vocapi/v1/customer")
public class VocCustController extends BaseController
{
    @Autowired
    private VocCustService service;

    /**
     * VOC고객 목록 조회
     * 
     * @param param VOC고객 검색 조건 포함 객체
     * @return 결과 행 및 페이지 정보를 포함한 PaginatedResponse 객체
     */
    @Operation(summary = "GET VOC고객 목록 조회", description = "VOC고객 목록 조회")
    @GetMapping("/list")
    public ResponseObject<VocCustDto.ListResponse> getVocCustList(HttpServletRequest req, VocCustDto.Request param)
    {
        return ResponseUtil.getResponse(req, service.getVocCustList(param));
    }
	
    /**
     * VOC고객 상세 조회
     * 
     * @param custSeq VOC고객 검색 조건 포함 객체
     * @return VOC고객 객체
     */
    @Operation(summary = "GET VOC고객 상세 조회", description = "vocSeq로 VOC고객 정보 1건 조회")
    @GetMapping("/{custSeq}")
    public ResponseObject<VocCustDto.Info> getVocCust(HttpServletRequest req
            , @Parameter(description = "고객일련번호") @PathVariable(name = "custSeq") int custSeq)
    {
        return ResponseUtil.getResponse(req, service.getVocCust(custSeq));
    }

    /**
     * VOC고객 삭제
     * 
     * @param custSeq 삭제할 VOC고객 key
     */
    @Operation(summary = "VOC 삭제", description = "VOC정보를 삭제한다.")
    @PostMapping("/delete/{custSeq}")
    public ResponseObject deleteVocCust(HttpServletRequest req
            , @Parameter(description = "고객일련번호") @PathVariable(name = "custSeq") int custSeq)
    {
        return ResponseUtil.getResponse(req, service.deleteVocCust(custSeq));
    }
}
