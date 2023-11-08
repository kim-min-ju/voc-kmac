package kr.co.voc.vocapi.system.mapper;

import kr.co.voc.vocapi.system.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 사용자 MyBatis Mapper 클래스
 * 
 * @ClassName UserMapper.java
 * @Description 사용자 Mapper 클래스
 * @author mjkim
 * @since 2023. 3. 3.
 *
 */
@Repository
@Mapper
public interface UserMapper
{
    /**
    /**
     * 사용자 목록 조회
     * 
     * @param param 사용자 검색 조건 포함 객체
     */
    List<UserDto.Info> getUserList(@Param("p") UserDto.Request param, @Param("offset") long offset, @Param("length") int length);

    /**
     * 사용자 목록 개수 조회
     * 
     * @param param 사용자 검색 조건 포함 객체
     * @return 조건에 해당되는 전체 행 수
     */
    long getUserListCount(UserDto.Request param);

    /**
     * 사용자 상세 조회
     *
     * @param userSeq 사용자 pk
     * @return 사용자 객체
     */
    UserDto.Info getUser(int userSeq);

    /**
     * 사용자 입력(insert)
     * 
     * @param param 저장 할 사용자 객체
     * @return 적용된 행 수
     */
    int insertUser(UserDto.Info param);

    /**
     * 사용자 수정(update)
     *
     * @param param 저장 할 사용자 객체
     * @return 적용된 행 수
     */
    int updateUser(UserDto.Info param);

    /**
     * 사용자 삭제
     * 
     * @param userSeq 사용자 pk
     * @return 적용된 행 수
     */
    int deleteUser(int userSeq);
}
