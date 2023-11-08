package kr.co.voc.vocapi.voc.mapper;

import kr.co.voc.vocapi.voc.dto.VocCustDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * VOC고객 MyBatis Mapper 클래스
 * 
 * @ClassName VocCustMapper.java
 * @Description VOC고객 Mapper 클래스
 * @author mjkim
 * @since 2023. 3. 3.
 *
 */
@Repository
@Mapper
public interface VocCustMapper
{
    /**
     * VOC고객 상세 조회
     * 
     * @param vocSeq VOC고객 pk
     * @return VOC고객 객체
     */
    VocCustDto.Info getVocCust(int vocSeq);

    /**
     * VOC고객 목록 조회
     * 
     * @param param VOC고객 검색 조건 포함 객체
     */
    List<VocCustDto.Info> getVocCustList(@Param("p") VocCustDto.Request param, @Param("offset") long offset, @Param("length") int length);

    /**
     * VOC고객 목록 개수 조회
     * 
     * @param param VOC고객 검색 조건 포함 객체
     * @return 조건에 해당되는 전체 행 수
     */
    long getVocCustListCount(VocCustDto.Request param);

    /**
     * VOC고객 저장(insert/update)
     * 
     * @param data 저장 할 VOC고객 객체
     * @return 적용된 행 수
     */
    int mergeVocCust(VocCustDto.Info data);

    /**
     * VOC고객 삭제
     * 
     * @param data 삭제 할 조건객체
     * @return 적용된 행 수
     */
    int deleteVocCust(VocCustDto.Info data);
}
