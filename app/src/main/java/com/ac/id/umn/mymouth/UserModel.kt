package com.ac.id.umn.mymouth

data class userAccount(
        var username: String,
        var email: String,
        var password: String,
        var activePhoto: Int,
        var photoInvent: List<Int>,
        var score: Int,
        var money: Int,
        var achievementList: List<Int>,
        var tutorial: String,
        var level: List<String>,
        var updatescore: Int,
        var level1score :  Int,
        var level2score :  Int,
        var level3score :  Int,
        var level4score :  Int,
        var level5score :  Int,
        var level6score :  Int,
        var level7score :  Int,
        var level8score :  Int,
        var level9score :  Int,
        var level10score :  Int

)

data class userLeaderboard(
        var username: String,
        var score: Int,
        var activePhoto: Int
)

data class shopList(
        var itemname: String,
        var price: Int,
        var imagebit: Int,
        var imgid: Int

)

data class avatarList(
        var itemname: String,
        var imagebit: Int,
        var imgid: Int
)

data class gameplay(
        var soal: String,
        var jawaban: String,
        var pila: String,
        var pilb: String,
        var pilc: String,
        var pild: String
)

data class userAchievement(
        var achievname: String,
        var imagebit: Int,
        var imgid: Int
)