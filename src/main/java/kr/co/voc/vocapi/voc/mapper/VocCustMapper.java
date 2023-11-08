package kr.co.voc.vocapi.voc.mapper;

import java.util.List;

import kr.co.voc.vocapi.voc.dto.VocMstDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * VOC마스터 MyBatis Mapper 클래스
 * 
 * @ClassName VocMstMapper.java
 * @Description VOC마스터 Mapper 클래스
 * @author mjkim
 * @since 2023. 3. 3.
 *
 */
@Repository
@Mapper
public interface VocMstMapper
{
    /**
     * VOC마스터 상세 조회
     * 
     * @param vocSeq VOC마스터 pk
     * @return VOC마스터 객체
     */
    VocMstDto.Info getVocMst(int vocSeq);

    /**
     * VOC마스터 목록 조회
     * 
     * @param param VOC마스터 검색 조건 포함 객체
     */
    List<VocMstDto.Info> getVocMstList(@Param("p") VocMstDto.Request param, @Param("offset") long offset, @Param("length") int length);

    /**
     * VOC마스터 목록 개수 조회
     * 
     * @param param VOC마스터 검색 조건 포함 객체
     * @return 조건에 해당되는 전체 행 수
     */
    long getVocMstListCount(VocMstDto.Request param);

    /**
     * VOC마스터 저장(insert/update)
     * 
     * @param data 저장 할 VOC마스터 객체
     * @return 적용된 행 수
     */
    int mergeVocMst(VocMstDto.Info data);

    /**
     * VOC마스터 삭제
     * 
     * @param data 삭제 할 조건객체
     * @return 적용된 행 수
     */
    int deleteVocMst(VocMstDto.Info data);
}
