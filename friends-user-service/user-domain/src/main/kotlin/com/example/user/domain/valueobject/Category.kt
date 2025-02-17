package com.example.user.domain.valueobject

enum class Category(
    val type: CategoryType
) {
    // SUBJECT 카테고리
    FREE_CHAT(CategoryType.SUBJECT),
    FANDOM(CategoryType.SUBJECT),
    GAME(CategoryType.SUBJECT),
    MUSIC(CategoryType.SUBJECT),
    FOOD(CategoryType.SUBJECT),
    CONSULTING(CategoryType.SUBJECT),
    ENTERTAINMENT(CategoryType.SUBJECT),
    SPORTS(CategoryType.SUBJECT),
    DAILY(CategoryType.SUBJECT),
    FITNESS(CategoryType.SUBJECT),
    TRAVEL(CategoryType.SUBJECT),
    HOBBY(CategoryType.SUBJECT),
    SOCIAL(CategoryType.SUBJECT),
    FASHION(CategoryType.SUBJECT),
    JOB(CategoryType.SUBJECT),
    SELF_DEV(CategoryType.SUBJECT),
    FINANCE(CategoryType.SUBJECT),

    // REGION 카테고리
    SEOUL(CategoryType.REGION),
    BUSAN(CategoryType.REGION),
    DAEGU(CategoryType.REGION),
    INCHEON(CategoryType.REGION),
    GWANGJU(CategoryType.REGION),
    DAEJEON(CategoryType.REGION),
    ULSAN(CategoryType.REGION),
    SEJONG(CategoryType.REGION),
    GYEONGGI(CategoryType.REGION),
    GANGWON(CategoryType.REGION),
    CHUNGBUK(CategoryType.REGION),
    CHUNGNAM(CategoryType.REGION),
    JEONBUK(CategoryType.REGION),
    JEONNAM(CategoryType.REGION),
    GYEONGBUK(CategoryType.REGION),
    GYEONGNAM(CategoryType.REGION),
    JEJU(CategoryType.REGION)
}