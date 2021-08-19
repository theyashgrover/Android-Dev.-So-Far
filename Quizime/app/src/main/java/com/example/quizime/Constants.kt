package com.example.quizime

object Constants {

    const val USER_NAME : String = "user_name"
    const val TOTAL_QUESTIONS : String = "total_questions"
    const val CORRECT_ANSWERS : String = "correct_answers"

    fun getQuestions(): ArrayList<Question>{
        val questionsList = ArrayList<Question>()

        //questions start here

        val que1 = Question(1,"Which Anime Character is this ?" ,
            R.drawable.img_1 ,
            "Monkey D. Luffy" , "Usopp" , "Marshal D. Teech" ,
            "Gol D. Roger" ,1)
        questionsList.add(que1)

        val que2 = Question(2,"Which Anime show does this Character belongs to ?" ,
            R.drawable.img_2 ,
            "One Piece" , "Naruto" , "Tokyo Revengers" ,
            "Tokyo Ghoul" ,4)
        questionsList.add(que2)

        val que3 = Question(3,"Which legendary show does this Character belongs to ?" ,
            R.drawable.img_3,
            "One Piece" , "Naruto" , "Tokyo Revengers" ,
            "Dragon Ball" ,4)
        questionsList.add(que3)

        val que4 = Question(4,"Is he Light Yagami ?" ,
            R.drawable.img_4,
            "Yes" , "definiteLy" , "You're wrong , he's awesome" ,
            "He's L" ,3)
        questionsList.add(que4)

        val que5 = Question(5,"Which fruit this guy ate ?" ,
            R.drawable.img_5,
            "Gumo Gumo ni" , "Mera Mera no Mi" , "Fire-Fire fruit" ,
            "Flame-Fist fruit" ,2)
        questionsList.add(que5)

        return questionsList
    }


}