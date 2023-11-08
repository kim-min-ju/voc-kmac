package kr.co.kmac.system.mapper;

import kr.co.kmac.system.dto.VoctypeDto;
import kr.co.kmac.system.dto.VoctypeMstDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * vOC유형 MyBatis Mapper 클래스
 * 
 * @ClassName VoctypeMapper.java
 * @Description vOC유형 Mapper 클래스
 * @voctypeor mjkim
 * @since 2023. 9. 18.
 *
 */
@Repository
@Mapper
public interface VoctypeMapper
{
    /**
     * Voc유형마스터 목록 조회
     * 
     * @param param Voc유형마스터 검색 조건 포함 객체
     */
    List<VoctypeMstDto.Info> getVoctypeMstList(VoctypeMstDto.Request param);

    /**
     * Voc유형마스터 목록 개수 조회
     * 
     * @param param Voc유형마스터 검색 조건 포함 객체
     * @return 조건에 해당되는 전체 행 수
     */
    long getVoctypeMstListCount(VoctypeMstDto.Request param);

    /**
     * Voc유형 목록 조회
     *
     * @param param Voc유형 검색 조건 포함 객체
     */
    List<VoctypeDto.Info> getVoctypeList(VoctypeDto.Request param);

    /**
     * Voc유형 목록 개수 조회
     *
     * @param param Voc유형 검색 조건 포함 객체
     * @return 조건에 해당되는 전체 행 수
     */
    long getVoctypeListCount(VoctypeDto.Request param);

    /**
     * Voc유형 목록 dropbox용 조회
     *
     * @param param Voc유형 검색 조건 포함 객체
     */
    List<VoctypeDto.Info> getVoctypeCodeList(VoctypeDto.Request param);


    /**
     * Voc유형마스터 상세 조회
     *
     * @param voctypeMstSeq Voc유형마스터 pk
     * @return Voc유형마스터 객체
     */
    VoctypeMstDto.Info getVoctypeMst(int voctypeMstSeq);

    /**
     * Voc유형 상세 조회
     *
     * @param voctypeSeq vOC유형 pk
     * @return vOC유형 객체
     */
    VoctypeDto.Info getVoctype(int voctypeSeq);

    /**
     * Voc유형마스터 입력(insert)
     * 
     * @param param 저장 할 vOC유형 객체
     * @return 적용된 행 수
     */
    int insertVoctypeMst(VoctypeMstDto.Info param);

    /**
     * Voc유형 입력(insert)
     *
     * @param param 저장 할 vOC유형 객체
     * @return 적용된 행 수
     */
    int insertVoctype(VoctypeDto.Info param);

    /**
     * Voc유형마스터 수정(update)
     *
     * @param param 저장 할 Voc유형마스터 객체
     * @return 적용된 행 수
     */
    int updateVoctypeMst(VoctypeMstDto.Info param);

    /**
     * Voc유형 수정(update)
     *
     * @param param 저장 할 Voc유형 객체
     * @return 적용된 행 수
     */
    int updateVoctype(VoctypeDto.Info param);

    /**
     * Voc유형마스터 삭제
     * 
     * @param voctypeMstSeq Voc유형마스터 pk
     * @return 적용된 행 수
     */
    int deleteVoctypeMst(int voctypeMstSeq);

    /**
     * Voc유형 삭제
     *
     * @param voctypeSeq Voc유형 pk
     * @return 적용된 행 수
     */
    int deleteVoctype(int voctypeSeq);
}
