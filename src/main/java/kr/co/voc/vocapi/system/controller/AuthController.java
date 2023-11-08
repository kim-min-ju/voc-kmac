package kr.co.voc.vocapi.system.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.voc.vocapi.common.util.ResponseUtil;
import kr.co.voc.vocapi.coreframework.common.response.ResponseObject;
import kr.co.voc.vocapi.coreframework.controller.BaseController;
import kr.co.voc.vocapi.system.dto.UserDto;
import kr.co.voc.vocapi.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 사용자 관리/작업 Controller
 * 
 * @ClassName UserController.java
 * @Description 사용자
 * @author mjkim
 * @since 2023. 3. 3.
 */
@Tag(name = "UserController", description = "사용자관리 API")
@RestController
@RequestMapping("/vocapi/v1/user")
public class UserController extends BaseController
{
    @Autowired
    private UserService service;

    /**
     * 사용자 목록 조회
     * 
     * @param param 사용자 검색 조건 포함 객체
     * @return 결과 행 및 페이지 정보를 포함한 PaginatedResponse 객체
     */
    @Operation(summary = "GET 사용자 목록 조회", description = "사용자 목록 조회")
    @GetMapping("/list")
    public ResponseObject<UserDto.ListResponse> getUserList(HttpServletRequest req, UserDto.Request param)
    {
        return ResponseUtil.getResponse(req, service.getUserList(param));
    }
	
    /**
     * 사용자 상세 조회
     * 
     * @param userSeq 사용자 검색 조건 포함 객체
     * @return 사용자 객체
     */
    @Operation(summary = "GET 사용자 상세 조회", description = "userSeq로 사용자 정보 1건 조회")
    @GetMapping("/{userSeq}")
    public ResponseObject<UserDto.Info> getUser(HttpServletRequest req
            , @Parameter(description = "고객일련번호") @PathVariable(name = "userSeq") int userSeq)
    {
        return ResponseUtil.getResponse(req, service.getUser(userSeq));
    }

    /**
     * 사용자 등록
     *ss
     * @param param 등록할 사용자 객체
     * @return 등록결과 객체
     */
    @Operation(summary = "사용자 등록", description = "사용자 정보를 등록한다.")
    @PostMapping("/add")
    public ResponseObject insertUser(HttpServletRequest req, @RequestBody UserDto.Info param)
    {
        return ResponseUtil.getResponse(req, service.insertUser(param));
    }

    /**
     * 사용자 수정
     *
     * @param param 수정할 사용자 객체
     * @return 수정결과 객체
     */
    @Operation(summary = "사용자 수정", description = "사용자 정보를 수정한다.")
    @PostMapping("/modify")
    public ResponseObject updateUser(HttpServletRequest req, @RequestBody UserDto.Info param)
    {
        return ResponseUtil.getResponse(req, service.updateUser(param));
    }

    /**end
     * 사용자 삭제
     * 
     * @param userSeq 삭제할 사용자 key
     */
    @Operation(summary = "VOC 삭제", description = "VOC정보를 삭제한다.")
    @PostMapping("/remove/{userSeq}")
    public ResponseObject deleteUser(HttpServletRequest req
            , @Parameter(description = "고객일련번호") @PathVariable(name = "userSeq") int userSeq)
    {
        return ResponseUtil.getResponse(req, service.deleteUser(userSeq));
    }
}
