package com.example.chat.domain.valueobject.type

enum class CategorySubType(
    val type: CategoryMainType
) {
    // SUBJECT 카테고리
    FREE_CHAT(CategoryMainType.SUBJECT),
    FANDOM(CategoryMainType.SUBJECT),
    GAME(CategoryMainType.SUBJECT),
    MUSIC(CategoryMainType.SUBJECT),
    FOOD(CategoryMainType.SUBJECT),
    CONSULTING(CategoryMainType.SUBJECT),
    ENTERTAINMENT(CategoryMainType.SUBJECT),
    SPORTS(CategoryMainType.SUBJECT),
    DAILY(CategoryMainType.SUBJECT),
    FITNESS(CategoryMainType.SUBJECT),
    TRAVEL(CategoryMainType.SUBJECT),
    HOBBY(CategoryMainType.SUBJECT),
    SOCIAL(CategoryMainType.SUBJECT),
    FASHION(CategoryMainType.SUBJECT),
    JOB(CategoryMainType.SUBJECT),
    SELF_DEV(CategoryMainType.SUBJECT),
    FINANCE(CategoryMainType.SUBJECT),

    // REGION 카테고리
    SEOUL(CategoryMainType.REGION),
    BUSAN(CategoryMainType.REGION),
    DAEGU(CategoryMainType.REGION),
    INCHEON(CategoryMainType.REGION),
    GWANGJU(CategoryMainType.REGION),
    DAEJEON(CategoryMainType.REGION),
    ULSAN(CategoryMainType.REGION),
    SEJONG(CategoryMainType.REGION),
    GYEONGGI(CategoryMainType.REGION),
    GANGWON(CategoryMainType.REGION),
    CHUNGBUK(CategoryMainType.REGION),
    CHUNGNAM(CategoryMainType.REGION),
    JEONBUK(CategoryMainType.REGION),
    JEONNAM(CategoryMainType.REGION),
    GYEONGBUK(CategoryMainType.REGION),
    GYEONGNAM(CategoryMainType.REGION),
    JEJU(CategoryMainType.REGION)
}

