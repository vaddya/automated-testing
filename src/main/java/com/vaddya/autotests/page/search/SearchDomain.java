package com.vaddya.autotests.page.search;

public enum SearchDomain {
    USERS,
    GROUPS,
    GAMES,
    MUSIC,
    MOVIE;

    public String capitalizedName() {
        return name().charAt(0) + name().substring(1).toLowerCase();
    }
}
