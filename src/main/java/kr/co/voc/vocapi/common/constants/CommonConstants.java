package kr.co.cgv.kioskapi.common.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author kyudam
 * @class CommonConstants
 * @description 키오스크 API 기본 상수 설정
 * @since 2022/02/25
 */
@Component
public class CommonConstants {
    private CommonConstants() {}
    // 시스템 설정
    public static String SYSTEM_MODE;       // 시스템 디버그 모드

    // 공통코드
    public static final String CO_CD = "A420";
    public static final String SITE_CD = "CGV";
    public static final String COMMGRP_CD = "3870";         // 장치 종류 코드
    public static final String LVL_COMMGRP_CD = "1280";
    public static final String AREA_COMMGRP_CD = "6937";

    // 공통 리턴
    public static final String SERVICE_AREA = "kiosk";
    public static final String SERVICE_URL = "/" + SERVICE_AREA;
    // 공통 응답 코드
    public static final String DEFAULT_KEY = "error.";
    public static final String DEFAULT_SUCCESS = "0000";
    public static final String BASE_SERVER_ERROR = "1008";
    public static final String BASE_MESSAGE_ERROR = "COMMON_99999";

    // 공통 USE Y / N
    public static final String USE_Y = "1";
    public static final String USE_N = "0";
    // 공통 Header
    public static final String X_HTTP_METHOD_OVERRIDE = "X-HTTP-Method-Override";
    // 영화 관련 URL
    public static String MOVIE_IMG_URL;
    public static String PHOTO_TKT_BASE_URL;
    public static String PHOTO_TKT_NEW_URL;
    public static String PHOTO_TKT_OLD_URL;

    public static final String SALE_PATH_CD_KIOSK = "03";   //키오스크 판매경로코드

    public static String KKO_PROFILE_KEY;                   // 카카오 알림톡 프로필키
    public static String KKO_EXCHANGE_ID = "EC0094";        // 카카오기프티콘 제휴사 브랜드ID ODL: CTCA13
    public static String COMMON_AES128_KEY;                // 공통 암호화 키

    public static String CUP_DPST_ENABLE_YN;                // 컵보증금 적용 여부

    public static String KMS_IMAGE_URL;                     // KMS 이미지 URL

    @Value("${system.mode}")
    public void setSystemMode(String systemMode) {
        SYSTEM_MODE = systemMode;
    }

    @Value("${kko.profileKey}")
    public void setKkoProfileKey(String kkoProfileKey) {
    	KKO_PROFILE_KEY = kkoProfileKey;
    }

    @Value("${image.path.poster}")
    public void setMovieImgUrl(String movieImgUrl) {
        MOVIE_IMG_URL = movieImgUrl;
    }

    @Value("${image.path.photoplay}")
    public void setPhotoTktBaseUrl(String photoTktBaseUrl) {
        PHOTO_TKT_BASE_URL = photoTktBaseUrl;
        PHOTO_TKT_NEW_URL = PHOTO_TKT_BASE_URL + "mphoto/";
        PHOTO_TKT_OLD_URL = PHOTO_TKT_BASE_URL + "Ticket/";
    }

    @Value("${encrypt.aes128key}")
    public void setCommonAes128Key(String commonAes128Key) {
        COMMON_AES128_KEY = commonAes128Key;
    }

    @Value("${cupDpst.enableYn}")
    public void setCupdpstEnableYn(String cupdpstEnableYn) {
        CUP_DPST_ENABLE_YN = cupdpstEnableYn;
    }

    @Value("${kmsimage.url}")
    public void setKmsImageUrl(String kmsImageUrl) {
        KMS_IMAGE_URL = kmsImageUrl;
    }
}
