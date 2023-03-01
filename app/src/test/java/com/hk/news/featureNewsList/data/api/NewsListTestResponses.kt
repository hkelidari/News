package com.hk.news.featureNewsList.data.api

val newsListSuccessfulResponse = """
    {
    "status": "ok",
    "totalResults": 2,
    "articles": [
    {
    "source": {
    "id": "bbc-news",
    "name": "BBC News"
    },
    "author": "BBC News",
    "title" : "Winter storm brings snow to California and tornadoes to central US",
    "description" : "California's severe weather had moved eastward, with seven tornadoes reported in Oklahoma.",
    "url": "http://www.bbc.co.uk/news/world-us-canada-64789869",
    "urlToImage": "https://ichef.bbci.co.uk/news/1024/branded_news/A7DE/production/_128747924_gettyimages-1469920015.jpg",
    "publishedAt" : "2023-02-27T15:52:22.2371023Z",
    "content" : "A winter storm that brought unprecedented snow to southern California has moved eastward, bringing with it tornadoes and powerful winds. \\r\\nResidents in Oklahoma, Kansas, Missouri and Texas were asked… [+2864 chars]"
    },
    {
    "source": {
    "id": "bbc-news",
    "name": "BBC News"
    },
    "author": "BBC News",
    "title" : "SAG Awards: Everything Everywhere All At Once dominates ahead of Oscars",
    "description" : "Everything Everywhere All At Once cements its status as a frontrunner following several major wins.",
    "url": "http://www.bbc.co.uk/news/entertainment-arts-64781317",
    "urlToImage": "https://ichef.bbci.co.uk/news/1024/branded_news/6BD6/production/_128760672_yeohgettyimages-1247521517.jpg",
    "publishedAt" : "2023-02-27T15:22:22.8935631Z",
    "content" : "Everything Everywhere All At Once has cemented its status as the Oscars frontrunner after several big wins at the Screen Actors Guild (SAG) Awards.\\r\\nThe multiverse adventure won best film cast at the… [+7738 chars]"
    }
    ]
    }
""".trimIndent()

const val errorResponse = "I am not a json :o"