package kr.co.voc.vocapi.system.service;

import kr.co.voc.vocapi.common.util.MessageUtil;
import kr.co.voc.vocapi.coreframework.service.BaseService;
import kr.co.voc.vocapi.system.dto.UserDto;
import kr.co.voc.vocapi.system.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 사용자 서비스 클래스
 * 
 * @ClassName UserService.java
 * @Description 사용자 서비스 클래스
 * @author mjkim
 * @since 2023. 3. 3.
 */
@Service
public class UserService extends BaseService
{
    // 사용자 조작 위한 DAO 객체
    @Autowired
    private UserMapper mapper;

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
     * 사용자 목록 조회
     * 
     * @param param 사용자 검색 조건 포함 객체
     * @return 결과 행 및 페이지 정보를 포함한 PaginatedResponse 객체
     */
    public UserDto.ListResponse getUserList(UserDto.Request param)
    {
        // offset 계산, -1인 경우 전체 반환
        int offset = param.getPageNumber() < 0 || param.getPageSize() == 0 ?
                -1:param.getPageNumber() * param.getPageSize();
        //long total = offset == -1 ? 0:mapper.getUserListCount(param);
        long total = mapper.getUserListCount(param);
        return UserDto.ListResponse
                .<UserDto.Info>builder()
                .pageNumber(param.getPageNumber())
                .pageSize(param.getPageSize())
                .totalCount(total)
                .list(mapper.getUserList(param, offset, param.getPageSize()))
                .build();
	}

    /**
     * 사용자 상세 조회
     * 
     * @param userSeq 사용자 pk
     * @return 사용자 객체
     */
    public UserDto.Info getUser(int userSeq)
    {
        return mapper.getUser(userSeq);
    }
    
    /**
     * 사용자 등록(insert)
     * 
     * @param param 등록할 사용자 객체
     * @return 적용된 행 수
     */
    @Transactional
    public UserDto.Response insertUser(UserDto.Info param) {
        UserDto.Response res = UserDto.Response.builder().build();

        res.setRtnCnt(mapper.insertUser(param));
        res.setRtnMessage(MessageUtil.getMessage("common.insert.0000"));
        return res;
    }

    /**
     * 사용자 수정(update)
     *
     * @param param 저장할 사용자 객체
     * @return 적용된 행 수
     */
    @Transactional
    public UserDto.Response updateUser(UserDto.Info param) {
        UserDto.Response res = UserDto.Response.builder().build();

        res.setRtnCnt(mapper.updateUser(param));
        res.setRtnMessage(MessageUtil.getMessage("common.update.0000"));
        return res;
    }

	
    /**
     * 사용자 삭제
     * 
     * @param userSeq 사용자 pk
     * @return 적용된 행 수
     */
    @Transactional
    public UserDto.Response deleteUser(int userSeq) {
        UserDto.Response res = UserDto.Response.builder().build();

        res.setRtnCnt(mapper.deleteUser(userSeq));
        res.setRtnMessage(MessageUtil.getMessage("common.delete.0000"));
        return res;
    }

}
