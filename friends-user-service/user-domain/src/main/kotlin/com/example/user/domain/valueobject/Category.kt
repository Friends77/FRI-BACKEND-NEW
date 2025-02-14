package com.example.user.domain.valueobject

enum class Category(
    val displayName: String,
    val type: CategoryType,
    val image: String? = null
) {
    // SUBJECT 카테고리
    FREE_CHAT("자유수다", CategoryType.SUBJECT, "🧑‍🧑‍🧒"),
    FANDOM("팬덤", CategoryType.SUBJECT, "🎈"),
    GAME("게임", CategoryType.SUBJECT, "🎮"),
    MUSIC("음악", CategoryType.SUBJECT, "🎵"),
    FOOD("맛집", CategoryType.SUBJECT, "🍽️"),
    CONSULTING("고민/상담", CategoryType.SUBJECT, "🗣️"),
    ENTERTAINMENT("엔터테인먼트", CategoryType.SUBJECT, "🎈"),
    SPORTS("스포츠", CategoryType.SUBJECT, "🏆"),
    DAILY("일상", CategoryType.SUBJECT, "☘️"),
    FITNESS("운동/건강", CategoryType.SUBJECT, "👟️"),
    TRAVEL("여행", CategoryType.SUBJECT, "✈️"),
    HOBBY("취미", CategoryType.SUBJECT, "🧸"),
    SOCIAL("친목/모임", CategoryType.SUBJECT, "👏"),
    FASHION("패션/뷰티", CategoryType.SUBJECT, "🧢"),
    JOB("직업", CategoryType.SUBJECT, "💼"),
    SELF_DEV("자기계발", CategoryType.SUBJECT, "📚"),
    FINANCE("재테크", CategoryType.SUBJECT, "💰"),

    // REGION 카테고리 (이미지 없음)
    SEOUL("서울", CategoryType.REGION),
    BUSAN("부산", CategoryType.REGION),
    DAEGU("대구", CategoryType.REGION),
    INCHEON("인천", CategoryType.REGION),
    GWANGJU("광주", CategoryType.REGION),
    DAEJEON("대전", CategoryType.REGION),
    ULSAN("울산", CategoryType.REGION),
    SEJONG("세종", CategoryType.REGION),
    GYEONGGI("경기도", CategoryType.REGION),
    GANGWON("강원도", CategoryType.REGION),
    CHUNGBUK("충청북도", CategoryType.REGION),
    CHUNGNAM("충청남도", CategoryType.REGION),
    JEONBUK("전라북도", CategoryType.REGION),
    JEONNAM("전라남도", CategoryType.REGION),
    GYEONGBUK("경상북도", CategoryType.REGION),
    GYEONGNAM("경상남도", CategoryType.REGION),
    JEJU("제주도", CategoryType.REGION)
}

