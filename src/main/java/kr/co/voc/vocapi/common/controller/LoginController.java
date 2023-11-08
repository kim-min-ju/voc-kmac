package kr.co.voc.vocapi.common.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.voc.vocapi.common.util.ResponseUtil;
import kr.co.voc.vocapi.coreframework.common.response.ResponseObject;
import kr.co.voc.vocapi.coreframework.controller.BaseController;
import kr.co.voc.vocapi.system.dto.CodeDto;
import kr.co.voc.vocapi.system.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 공통 관리/작업 Controller
 * 
 * @ClassName CommonController.java
 * @Description 첨부파일
 * @author mjkim
 * @since 2023. 3. 3.
 */
@Tag(name = "CommonController", description = "공통 관리 API")
@RestController
@RequestMapping("/vocapi/v1/common")
public class CommonController extends BaseController
{
    @Autowired
    private CodeService service;

    /**
     * 코드 목록[콤보박스용] 조회
     *
     * @param param 코드목록 조회할 조건 객체
     */
    @Operation(summary = "공통코드 목록조회", description = "콤보박스용 공통코드 목록을 조회한다.")
    @GetMapping("/code/list")
    public ResponseObject<CodeDto.ListResponse> getCodeList(HttpServletRequest req, CodeDto.Request param)
    {
        param.setPageNumber(0);
        param.setPageSize(0);
        return ResponseUtil.getResponse(req, service.getCodeList(param));
    }
}
