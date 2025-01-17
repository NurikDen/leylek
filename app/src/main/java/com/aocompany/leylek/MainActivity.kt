package com.aocompany.leylek
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.RotateAnimation
import android.view.animation.ScaleAnimation
import android.view.animation.TranslateAnimation
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import org.w3c.dom.Document
import javax.xml.parsers.DocumentBuilderFactory


@Suppress("DEPRECATION")
open class MainActivity : AppCompatActivity() {
    private var mExplosionField: ExplosionField? = null
    @SuppressLint("MissingInflatedId", "CutPasteId", "ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<ConstraintLayout>(R.id.button)
        val name = findViewById<TextView>(R.id.name)
        val meaning = findViewById<TextView>(R.id.meaning)
        val result = findViewById<ImageView>(R.id.result)
        val character_type = findViewById<TextView>(R.id.character_type)
        val men_women = findViewById<ImageView>(R.id.men_button)
        val egg_view = findViewById<ImageView>(R.id.egg)
        val help = findViewById<ImageView>(R.id.question_button_white)
        val background = findViewById<ImageView>(R.id.background)
        val wings = findViewById<ImageView>(R.id.wings)
        var celebrity_name1 = ""
        var celebrity_name2 = ""
        var celebrity_name3 = ""
        var celebrity_meaning1 = ""
        var celebrity_meaning2 = ""
        var celebrity_meaning3 = ""
        var is_celebrity_meaning1 = false
        var is_celebrity_meaning2 = false
        var is_celebrity_meaning3 = false
        var is_helping = false

        val celebrity_person1 = Result_celebrity(findViewById(R.id.resultLayout_1),findViewById(R.id.resultCelebrity1),findViewById(R.id.nameCelebrity1),findViewById(R.id.information_button1))
        val celebrity_person2 = Result_celebrity(findViewById(R.id.resultLayout_2),findViewById(R.id.resultCelebrity2),findViewById(R.id.nameCelebrity2),findViewById(R.id.information_button2))
        val celebrity_person3 = Result_celebrity(findViewById(R.id.resultLayout_3),findViewById(R.id.resultCelebrity3),findViewById(R.id.nameCelebrity3),findViewById(R.id.information_button3))

        val quotes_text = findViewById<TextView>(R.id.quotes_text)
        val quotes_author = findViewById<TextView>(R.id.quotes_author)

        var searching_tool = Searching()
        var quotesSearching = Quotes_searching()
        var celebritiesSearching = Celebrities_searching()

        val rich_name = Name(findViewById(R.id.rich),findViewById(R.id.rich_button),findViewById(R.id.rich_text),false,0f,0f,0f,0f,0f,0f,0)
        val strong_name = Name(findViewById(R.id.strong),findViewById(R.id.strong_button),findViewById(R.id.strong_text),false,0f,0f,0f,0f,0f,0f,0)
        val smart_name = Name(findViewById(R.id.smart),findViewById(R.id.smart_button),findViewById(R.id.smart_text),false,0f,0f,0f,0f,0f,0f,0)
        val beautiful_name = Name(findViewById(R.id.beautiful),findViewById(R.id.beautiful_button),findViewById(R.id.beautiful_text),false,0f,0f,0f,0f,0f,0f,0)
        val believer_name = Name(findViewById(R.id.believer),findViewById(R.id.believer_button),findViewById(R.id.believer_text),false,0f,0f,0f,0f,0f,0f,0)

        val place_1 = Place(findViewById(R.id.place1),"",true)
        val place_2 = Place(findViewById(R.id.place2),"",true)
        val place_3 = Place(findViewById(R.id.place3),"",true)
        val place_4 = Place(findViewById(R.id.place4),"",true)
        val place_5 = Place(findViewById(R.id.place5),"",true)

        val fadeOutAnimation = AnimationUtils.loadAnimation(this, R.anim.fadeout)
        val fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fadein)
        val fadeOutWings = AnimationUtils.loadAnimation(this, R.anim.wingsfadeout)
        fadeOutWings.fillAfter = true
        fadeOutAnimation.fillAfter = true
        fadeInAnimation.fillAfter = true

        mExplosionField = ExplosionField.attach2Window(this)

        val sharedPreferences = getSharedPreferences("com.aocompany.leylek", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        var isFirstRun = sharedPreferences.getBoolean("isFirstRun", true)

        val rotationAnimator = ObjectAnimator.ofFloat(wings, "rotation", 0f, -120f)
        rotationAnimator.duration = 600

        val shaking = TranslateAnimation(
            0f, 0f,
            0f,
            100f)
        shaking.duration = 600
        shaking.fillAfter = true
        quotes_text.startAnimation(fadeInAnimation)
        var count_click = 0

        val wings_anim = TranslateAnimation(
            40f, 120f,
            0f,
            -70f)
        wings_anim.duration = 800
        wings_anim.fillAfter = true
        val egg_scale = ScaleAnimation(0.8f,1f,0.8f,1f, Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f)
        egg_scale.duration = 900


        help.setOnClickListener {
            quotes_author.text = ""
            if(is_helping == false) {
                is_helping = true
                egg_view.y -= 200f
                wings.y -=200f
                egg_view.visibility = View.VISIBLE
                wings.visibility = View.VISIBLE
                quotes_text.y = egg_view.y+200f
                quotes_text.x += 100f
                quotes_text.text =
                    "Сәлам, сорау булса, берәр төймәгә бас. Мин сиңа шул төймәнең функциясе турында сөйләрмен"
                egg_view.startAnimation(egg_scale)
                wings.startAnimation(wings_anim)
                rotationAnimator.start()
                egg_view.setImageResource(R.drawable.leylek_happy)
                quotes_text.startAnimation(egg_scale)
            }
            else if(is_helping == true) {

                wings.startAnimation(fadeOutWings)
                egg_view.startAnimation(fadeOutWings)
                is_helping = false
                egg_view.visibility = View.INVISIBLE
                quotes_text.x -= 100f
                quotes_text.y = egg_view.y
                quotes_text.text = ""
                quotes_author.text = ""
                quotes_text.y = quotes_author.y-400f
                egg_view.y += 200f
                wings.y +=200f
            }
        }
        if (isFirstRun) {
            help.visibility = View.INVISIBLE
            egg_view.visibility = View.VISIBLE
            background.visibility = View.INVISIBLE
            men_women.visibility = View.INVISIBLE
            rich_name.get_imageView().visibility = View.INVISIBLE
            strong_name.get_imageView().visibility = View.INVISIBLE
            smart_name.get_imageView().visibility = View.INVISIBLE
            beautiful_name.get_imageView().visibility = View.INVISIBLE
            believer_name.get_imageView().visibility = View.INVISIBLE
            rich_name.get_textView().visibility = View.INVISIBLE
            strong_name.get_textView().visibility = View.INVISIBLE
            smart_name.get_textView().visibility = View.INVISIBLE
            beautiful_name.get_textView().visibility = View.INVISIBLE
            believer_name.get_textView().visibility = View.INVISIBLE
            place_1.get_placeView().visibility = View.INVISIBLE
            place_2.get_placeView().visibility = View.INVISIBLE
            place_3.get_placeView().visibility = View.INVISIBLE
            place_4.get_placeView().visibility = View.INVISIBLE
            place_5.get_placeView().visibility = View.INVISIBLE
            button.visibility = View.INVISIBLE
            quotes_text.setText("Сәлам, төймәгә бас")
            quotes_text.startAnimation(egg_scale)
            editor.putBoolean("isFirstRun", false)
            editor.apply()
            help.isClickable = false
        }
        else{
            val anim_men = TranslateAnimation(
                0f, 0f,
                -300f,
                0f)
            anim_men.duration = 1000
            anim_men.fillAfter = true
            men_women.startAnimation(anim_men)
            val translate_button_animation = TranslateAnimation(
                0f, 0f,
                200f,
                0f)
            translate_button_animation.duration = 900
            button.startAnimation(translate_button_animation)
        }
        egg_view.setOnClickListener {
            if(isFirstRun) {
                quotes_author.text = ""
                count_click += 1
                egg_view.visibility  = View.VISIBLE
                if (count_click in 0..4) {
                    quotes_text.text = ""
                    egg_view.startAnimation(shaking)
                }
                when(count_click){
                    1->egg_view.setImageResource(R.drawable.egg_2)
                    2->egg_view.setImageResource(R.drawable.egg_3)
                    3->egg_view.setImageResource(R.drawable.egg_4)
                    4->egg_view.setImageResource(R.drawable.egg_5)
                }
                if (count_click == 5) {
                    egg_view.setImageResource(R.drawable.leylek_happy)
                    wings.visibility = View.VISIBLE
                    shaking.fillAfter = true
                    shaking.duration = 1000
                    egg_view.startAnimation(shaking)
                    wings.startAnimation(fadeInAnimation)
                    wings.startAnimation(shaking)
                } else if (count_click == 6) {
                    egg_view.startAnimation(egg_scale)
                    wings.startAnimation(wings_anim)
                    rotationAnimator.start()
                    quotes_text.y = egg_view.y
                    quotes_text.x += 100f
                    quotes_text.text = "Сәлам, минем исемем Ләйләк"
                    quotes_text.startAnimation(egg_scale)
                } else if (count_click == 7) {
                    egg_view.startAnimation(egg_scale)
                    wings.startAnimation(wings_anim)
                    rotationAnimator.start()
                    quotes_text.text = "Мин кешеләргә иң матур һәм мәгънәле исем табарга булышам"
                    quotes_text.startAnimation(egg_scale)
                } else if (count_click == 8) {
                    egg_view.startAnimation(egg_scale)
                    wings.startAnimation(wings_anim)
                    rotationAnimator.start()
                    quotes_text.text = "Берәр сыйфатны сайлагыз"
                    quotes_text.startAnimation(egg_scale)
                    rich_name.get_imageView().visibility = View.VISIBLE
                    strong_name.get_imageView().visibility = View.VISIBLE
                    smart_name.get_imageView().visibility = View.VISIBLE
                    beautiful_name.get_imageView().visibility = View.VISIBLE
                    believer_name.get_imageView().visibility = View.VISIBLE
                    rich_name.get_textView().visibility = View.VISIBLE
                    strong_name.get_textView().visibility = View.VISIBLE
                    smart_name.get_textView().visibility = View.VISIBLE
                    beautiful_name.get_textView().visibility = View.VISIBLE
                    believer_name.get_textView().visibility = View.VISIBLE
                } else if (count_click == 9) {
                    egg_view.startAnimation(egg_scale)
                    wings.startAnimation(wings_anim)
                    rotationAnimator.start()
                    quotes_text.text = "Шәп сайлау!"
                    quotes_text.startAnimation(egg_scale)
                }
                else if (count_click == 10) {
                    quotes_author.text = ""
                    var arr = searching_tool.searching(rich_name.get_count(),strong_name.get_count(),smart_name.get_count(),beautiful_name.get_count(),believer_name.get_count(),"man",
                        getXmlDocument("man_names.xml")!!,
                        getXmlDocument("man_names.xml")!!.getElementsByTagName("name"), getXmlDocument("man_names.xml")!!.getElementsByTagName("character"))
                    findViewById<ConstraintLayout>(R.id.resultLayout).startAnimation(fadeInAnimation)
                    name.setText(arr[0])
                    meaning.setText(arr[1])
                    character_type.setText(arr[2])
                    result.visibility = View.VISIBLE
                    egg_view.startAnimation(egg_scale)
                    wings.startAnimation(wings_anim)
                    rotationAnimator.start()
                    quotes_text.text = "Бигрәк матур исем! Хәзер сез бу исемнең мәгънәсен карый аласыз!"
                    quotes_text.startAnimation(egg_scale)
                }
             else if (count_click == 11) {
                 wings.startAnimation(wings_anim)
                egg_view.startAnimation(egg_scale)
                rotationAnimator.start()
                quotes_text.text =
                    "Ярар, сораулар булса мине монда таба аласыз, очрашуларга кадәр! Безнең кушымтаны сайлагавыгыз өчен зур рәхмәт!)"
                quotes_text.startAnimation(egg_scale)
                help.visibility = View.VISIBLE
            }
                else if (count_click == 12) {
                    quotes_text.x -=100f
                    wings.startAnimation(fadeOutWings)
                    egg_view.visibility = View.INVISIBLE
                    quotes_text.y = quotes_author.y-400f
                    quotes_text.startAnimation(egg_scale)
                    background.visibility = View.VISIBLE
                    men_women.visibility = View.VISIBLE
                    button.visibility = View.VISIBLE
                    quotes_text.text = ""
                    isFirstRun = false
                    count_click = 0
                    help.isClickable = true
                }
            }
        }
        button.setOnClickListener {
            if (is_helping == false) {
                quotes_author.text = ""
                var arr = searching_tool.searching(
                    rich_name.get_count(),
                    strong_name.get_count(),
                    smart_name.get_count(),
                    beautiful_name.get_count(),
                    believer_name.get_count(),
                    "man",
                    getXmlDocument("man_names.xml")!!,
                    getXmlDocument("man_names.xml")!!.getElementsByTagName("name"),
                    getXmlDocument("man_names.xml")!!.getElementsByTagName("character")
                )
                findViewById<ConstraintLayout>(R.id.resultLayout).startAnimation(fadeInAnimation)
                name.setText(arr[0])
                meaning.setText(arr[1])
                character_type.setText(arr[2])
                result.visibility = View.VISIBLE
                celebrity_person1.get_textView().setTextSize(16f)
                celebrity_person2.get_textView().setTextSize(16f)
                celebrity_person3.get_textView().setTextSize(16f)
                var nodeList = celebritiesSearching.celebrities_searching(
                    getXmlDocument("men_celebrity.xml")!!,
                    arr[0]
                )
                if (nodeList.size == 0) {
                    quotes_text.setText("Әлегә мондый исемле бөек шәхесләр табылмаган. Бәлки сезнең балагыз бу исемне йөртеп бөек шәхес булыр?")
                    quotes_author.setText("")
                    celebrity_person1.get_imageView().visibility = View.INVISIBLE
                    celebrity_person2.get_imageView().visibility = View.INVISIBLE
                    celebrity_person3.get_imageView().visibility = View.INVISIBLE
                    celebrity_person1.get_infoView().visibility = View.INVISIBLE
                    celebrity_person2.get_infoView().visibility = View.INVISIBLE
                    celebrity_person3.get_infoView().visibility = View.INVISIBLE
                    celebrity_person1.get_textView().setText("")
                    celebrity_person2.get_textView().setText("")
                    celebrity_person3.get_textView().setText("")
                    if (!rich_name.get_isNameRunning()) {
                        rich_name.get_imageView().visibility = View.INVISIBLE
                        rich_name.get_textView().visibility = View.INVISIBLE
                    }
                    if (!strong_name.get_isNameRunning()) {
                        strong_name.get_imageView().visibility = View.INVISIBLE
                        strong_name.get_textView().visibility = View.INVISIBLE
                    }
                    if (!smart_name.get_isNameRunning()) {
                        smart_name.get_imageView().visibility = View.INVISIBLE
                        smart_name.get_textView().visibility = View.INVISIBLE
                    }
                    if (!beautiful_name.get_isNameRunning()) {
                        beautiful_name.get_imageView().visibility = View.INVISIBLE
                        beautiful_name.get_textView().visibility = View.INVISIBLE
                    }
                    if (!believer_name.get_isNameRunning()) {
                        believer_name.get_imageView().visibility = View.INVISIBLE
                        believer_name.get_textView().visibility = View.INVISIBLE
                    }
                } else if (nodeList.size == 2) {
                    celebrity_person1.get_imageView().visibility = View.VISIBLE
                    celebrity_person2.get_imageView().visibility = View.INVISIBLE
                    celebrity_person3.get_imageView().visibility = View.INVISIBLE
                    celebrity_person1.get_infoView().visibility = View.VISIBLE
                    celebrity_person2.get_infoView().visibility = View.INVISIBLE
                    celebrity_person3.get_infoView().visibility = View.INVISIBLE
                    celebrity_person2.get_textView().setText("")
                    celebrity_person3.get_textView().setText("")
                    val anim_celebrity = TranslateAnimation(
                        -500f, 0f,
                        0f,
                        0f
                    )
                    anim_celebrity.duration = 800
                    anim_celebrity.fillAfter = true
                    celebrity_person1.get_constraintLayout().startAnimation(anim_celebrity)
                    quotes_text.setText("")
                    quotes_author.setText("")
                    celebrity_person1.get_textView().setText(nodeList[0])
                    celebrity_name1 = nodeList[0]
                    celebrity_meaning1 = nodeList[1]
                    if (!rich_name.get_isNameRunning()) {
                        rich_name.get_imageView().visibility = View.INVISIBLE
                        rich_name.get_textView().visibility = View.INVISIBLE
                    }
                    if (!strong_name.get_isNameRunning()) {
                        strong_name.get_imageView().visibility = View.INVISIBLE
                        strong_name.get_textView().visibility = View.INVISIBLE
                    }
                    if (!smart_name.get_isNameRunning()) {
                        smart_name.get_imageView().visibility = View.INVISIBLE
                        smart_name.get_textView().visibility = View.INVISIBLE
                    }
                    if (!beautiful_name.get_isNameRunning()) {
                        beautiful_name.get_imageView().visibility = View.INVISIBLE
                        beautiful_name.get_textView().visibility = View.INVISIBLE
                    }
                    if (!believer_name.get_isNameRunning()) {
                        believer_name.get_imageView().visibility = View.INVISIBLE
                        believer_name.get_textView().visibility = View.INVISIBLE
                    }
                } else if (nodeList.size == 4) {
                    celebrity_person1.get_imageView().visibility = View.VISIBLE
                    celebrity_person2.get_imageView().visibility = View.VISIBLE
                    celebrity_person3.get_imageView().visibility = View.INVISIBLE
                    celebrity_person1.get_infoView().visibility = View.VISIBLE
                    celebrity_person2.get_infoView().visibility = View.VISIBLE
                    celebrity_person3.get_infoView().visibility = View.INVISIBLE
                    celebrity_person3.get_textView().setText("")
                    val anim_celebrity = TranslateAnimation(
                        -500f, 0f,
                        0f,
                        0f
                    )
                    celebrity_name1 = nodeList[0]
                    celebrity_meaning1 = nodeList[1]
                    celebrity_name2 = nodeList[2]
                    celebrity_meaning2 = nodeList[3]
                    anim_celebrity.duration = 800
                    anim_celebrity.fillAfter = true
                    quotes_text.setText("")
                    quotes_author.setText("")
                    celebrity_person1.get_constraintLayout().startAnimation(anim_celebrity)
                    celebrity_person2.get_constraintLayout().startAnimation(anim_celebrity)
                    celebrity_person1.get_textView().setText(nodeList[0])
                    celebrity_person2.get_textView().setText(nodeList[2])
                    if (!rich_name.get_isNameRunning()) {
                        rich_name.get_imageView().visibility = View.INVISIBLE
                        rich_name.get_textView().visibility = View.INVISIBLE
                    }
                    if (!strong_name.get_isNameRunning()) {
                        strong_name.get_imageView().visibility = View.INVISIBLE
                        strong_name.get_textView().visibility = View.INVISIBLE
                    }
                    if (!smart_name.get_isNameRunning()) {
                        smart_name.get_imageView().visibility = View.INVISIBLE
                        smart_name.get_textView().visibility = View.INVISIBLE
                    }
                    if (!beautiful_name.get_isNameRunning()) {
                        beautiful_name.get_imageView().visibility = View.INVISIBLE
                        beautiful_name.get_textView().visibility = View.INVISIBLE
                    }
                    if (!believer_name.get_isNameRunning()) {
                        believer_name.get_imageView().visibility = View.INVISIBLE
                        believer_name.get_textView().visibility = View.INVISIBLE
                    }
                } else if (nodeList.size == 6) {
                    celebrity_person1.get_imageView().visibility = View.VISIBLE
                    celebrity_person2.get_imageView().visibility = View.VISIBLE
                    celebrity_person3.get_imageView().visibility = View.VISIBLE
                    celebrity_person1.get_infoView().visibility = View.VISIBLE
                    celebrity_person2.get_infoView().visibility = View.VISIBLE
                    celebrity_person3.get_infoView().visibility = View.VISIBLE
                    quotes_text.setText("")
                    quotes_author.setText("")
                    val anim_celebrity = TranslateAnimation(
                        -500f, 0f,
                        0f,
                        0f
                    )
                    celebrity_name1 = nodeList[0]
                    celebrity_meaning1 = nodeList[1]
                    celebrity_name2 = nodeList[2]
                    celebrity_meaning2 = nodeList[3]
                    celebrity_name3 = nodeList[4]
                    celebrity_meaning3 = nodeList[5]
                    anim_celebrity.duration = 800
                    anim_celebrity.fillAfter = true
                    quotes_text.setText("")
                    quotes_author.setText("")
                    celebrity_person1.get_constraintLayout().startAnimation(anim_celebrity)
                    celebrity_person2.get_constraintLayout().startAnimation(anim_celebrity)
                    celebrity_person3.get_constraintLayout().startAnimation(anim_celebrity)
                    celebrity_person1.get_textView().setText(nodeList[0])
                    celebrity_person2.get_textView().setText(nodeList[2])
                    celebrity_person3.get_textView().setText(nodeList[4])
                    if (!rich_name.get_isNameRunning()) {
                        rich_name.get_imageView().visibility = View.INVISIBLE
                        rich_name.get_textView().visibility = View.INVISIBLE
                    }
                    if (!strong_name.get_isNameRunning()) {
                        strong_name.get_imageView().visibility = View.INVISIBLE
                        strong_name.get_textView().visibility = View.INVISIBLE
                    }
                    if (!smart_name.get_isNameRunning()) {
                        smart_name.get_imageView().visibility = View.INVISIBLE
                        smart_name.get_textView().visibility = View.INVISIBLE
                    }
                    if (!beautiful_name.get_isNameRunning()) {
                        beautiful_name.get_imageView().visibility = View.INVISIBLE
                        beautiful_name.get_textView().visibility = View.INVISIBLE
                    }
                    if (!believer_name.get_isNameRunning()) {
                        believer_name.get_imageView().visibility = View.INVISIBLE
                        believer_name.get_textView().visibility = View.INVISIBLE
                    }
                }
                quotes_text.startAnimation(fadeInAnimation)
            }
            else if(is_helping == true){
                quotes_text.text = "Исем табу өчен бу төймәгә баса аласыз"
                quotes_text.startAnimation(egg_scale)
                egg_view.startAnimation(egg_scale)
                wings.startAnimation(wings_anim)
                rotationAnimator.start()
            }
        }
        men_women.setOnClickListener {
            if(is_helping == false){
            val intent = Intent(this, WomanMainActivity::class.java)
            startActivity(intent)
            }
            else if (is_helping == true){
                egg_view.startAnimation(egg_scale)
                wings.startAnimation(wings_anim)
                rotationAnimator.start()
                quotes_text.text = "Бу төймәгә бассагыз, хатын-кыз исемнәренә күчә аласыз"
                quotes_text.startAnimation(egg_scale)
            }
        }
        val anim_rich = ScaleAnimation(0f,1f,0f,1f, Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f)
        anim_rich.duration = 1000
        anim_rich.fillAfter = true
        findViewById<ConstraintLayout>(R.id.rich).startAnimation(anim_rich)

        val anim_strong = ScaleAnimation(0f,1f,0f,1f, Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f)
        anim_strong.duration = 1000
        anim_strong.fillAfter = true
        findViewById<ConstraintLayout>(R.id.strong).startAnimation(anim_strong)

        val anim_smart = ScaleAnimation(0f,1f,0f,1f, Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f)
        anim_smart.duration = 1000
        anim_smart.fillAfter = true
        findViewById<ConstraintLayout>(R.id.smart).startAnimation(anim_smart)

        val anim_beautiful = ScaleAnimation(0f,1f,0f,1f, Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f)
        anim_beautiful.duration = 1000
        anim_beautiful.fillAfter = true
        findViewById<ConstraintLayout>(R.id.beautiful).startAnimation(anim_beautiful)

        val anim_believer = ScaleAnimation(0f,1f,0f,1f, Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f)
        anim_believer.duration = 1000
        anim_believer.fillAfter = true
        findViewById<ConstraintLayout>(R.id.believer).startAnimation(anim_believer)

        celebrity_person1.get_imageView().setOnClickListener {
            if(is_helping == true){
                egg_view.startAnimation(egg_scale)
                wings.startAnimation(wings_anim)
                rotationAnimator.start()
                quotes_text.text = "Монда син бөек шәхесләрне карый аласыз"
                quotes_text.startAnimation(egg_scale)
            }
        }
        celebrity_person2.get_imageView().setOnClickListener {
            if(is_helping == true){
                egg_view.startAnimation(egg_scale)
                wings.startAnimation(wings_anim)
                rotationAnimator.start()
                quotes_text.text = "Монда син бөек шәхесләрне карый аласыз"
                quotes_text.startAnimation(egg_scale)
            }
        }
        celebrity_person3.get_imageView().setOnClickListener {
            if(is_helping == true){
                egg_view.startAnimation(egg_scale)
                wings.startAnimation(wings_anim)
                rotationAnimator.start()
                quotes_text.text = "Монда син бөек шәхесләрне карый аласыз"
                quotes_text.startAnimation(egg_scale)
            }
        }

        rich_name.get_imageView().setOnClickListener {
            if(is_helping == false) {
                if (rich_name.get_isNameRunning() == false) {
                    rich_name.get_constraintLayout().startAnimation(fadeOutAnimation)
                    fadeOutAnimation.setAnimationListener(object : Animation.AnimationListener {
                        override fun onAnimationStart(animation: Animation) {}
                        override fun onAnimationEnd(animation: Animation) {
                            if (place_1.get_placeIsEmpty() == true) {
                                rich_name.set_placeCount_x(place_1.get_placeView().x)
                                rich_name.set_placeCount_y(place_1.get_placeView().y)
                                place_1.get_placeView().visibility = View.INVISIBLE
                                rich_name.set_nameDestination_x(-7f)
                                rich_name.set_nameDestination_y(10f)
                                rich_name.set_name_textDestination_x(0f)
                                rich_name.set_name_textDestination_y(0f)
                                rich_name.set_count(5)
                                place_1.set_placeText("rich")
                                place_1.set_placeIsEmpty(false)
                            } else if (place_2.get_placeIsEmpty() == true) {
                                rich_name.set_placeCount_x(place_2.get_placeView().x)
                                rich_name.set_placeCount_y(place_2.get_placeView().y)
                                place_2.get_placeView().visibility = View.INVISIBLE
                                rich_name.set_nameDestination_x(-9f)
                                rich_name.set_nameDestination_y(-10f)
                                rich_name.set_name_textDestination_x(0f)
                                rich_name.set_name_textDestination_y(50f)
                                rich_name.set_count(4)
                                place_2.set_placeText("rich")
                                place_2.set_placeIsEmpty(false)
                                rich_name.get_imageView().rotation = 180f
                            } else if (place_3.get_placeIsEmpty() == true) {
                                rich_name.set_placeCount_x(place_3.get_placeView().x)
                                rich_name.set_placeCount_y(place_3.get_placeView().y)
                                place_3.get_placeView().visibility = View.INVISIBLE
                                rich_name.set_nameDestination_x(7f)
                                rich_name.set_nameDestination_y(10f)
                                rich_name.set_name_textDestination_x(0f)
                                rich_name.set_name_textDestination_y(0f)
                                rich_name.set_count(3)
                                place_3.set_placeText("rich")
                                place_3.set_placeIsEmpty(false)
                            } else if (place_4.get_placeIsEmpty() == true) {
                                rich_name.set_placeCount_x(place_4.get_placeView().x)
                                rich_name.set_placeCount_y(place_4.get_placeView().y)
                                place_4.get_placeView().visibility = View.INVISIBLE
                                rich_name.set_nameDestination_x(-7f)
                                rich_name.set_nameDestination_y(-10f)
                                rich_name.set_name_textDestination_x(0f)
                                rich_name.set_name_textDestination_y(50f)
                                rich_name.set_count(2)
                                rich_name.get_imageView().rotation = 180f
                                place_4.set_placeText("rich")
                                place_4.set_placeIsEmpty(false)
                            } else if (place_5.get_placeIsEmpty() == true) {
                                rich_name.set_placeCount_x(place_5.get_placeView().x)
                                rich_name.set_placeCount_y(place_5.get_placeView().y)
                                place_5.get_placeView().visibility = View.INVISIBLE
                                rich_name.set_nameDestination_x(-7f)
                                rich_name.set_nameDestination_y(10f)
                                rich_name.set_name_textDestination_x(0f)
                                rich_name.set_name_textDestination_y(0f)
                                rich_name.set_count(1)
                                place_5.set_placeText("rich")
                                place_5.set_placeIsEmpty(false)
                            }
                            rich_name.set_isNameRunning(true)
                            rich_name.get_constraintLayout().x =
                                rich_name.get_placeCount_x() + rich_name.get_nameDestination_x()
                            rich_name.get_constraintLayout().y =
                                rich_name.get_placeCount_y() + rich_name.get_nameDestination_y()
                            rich_name.get_textView().x += rich_name.get_name_textDestination_x()
                            rich_name.get_textView().y += rich_name.get_name_textDestination_y()
                            rich_name.get_constraintLayout().startAnimation(fadeInAnimation)
                            if (place_1.get_placeIsEmpty() == false && place_2.get_placeIsEmpty() == false && place_3.get_placeIsEmpty() == false && place_4.get_placeIsEmpty() == false && place_5.get_placeIsEmpty() == false) {
                                var nodeList =
                                    quotesSearching.quotes_searching(getXmlDocument("quotes.xml")!!)
                                quotes_author.setText(nodeList[0])
                                quotes_text.setText(nodeList[1])
                            }
                        }

                        override fun onAnimationRepeat(animation: Animation) {}
                    })
                }
            }
            else if(is_helping==true){
                quotes_text.text = "Бу төймәгә бассагыз, бай дигән мәгънәне белдергән исемнәр чыга"
                quotes_text.startAnimation(egg_scale)
            }
        }
        strong_name.get_imageView().setOnClickListener {
            if(is_helping==false){
            if (strong_name.get_isNameRunning() == false) {
                strong_name.get_constraintLayout().startAnimation(fadeOutAnimation)
                fadeOutAnimation.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation) {}
                    override fun onAnimationEnd(animation: Animation) {
                        if (place_1.get_placeIsEmpty() == true) {
                            strong_name.set_placeCount_x(place_1.get_placeView().x)
                            strong_name.set_placeCount_y(place_1.get_placeView().y)
                            place_1.get_placeView().visibility = View.INVISIBLE
                            strong_name.set_nameDestination_x(-7f)
                            strong_name.set_nameDestination_y(-10f)
                            strong_name.set_name_textDestination_x(0f)
                            strong_name.set_name_textDestination_y(0f)
                            strong_name.set_count(5)
                            place_1.set_placeText("strong")
                            place_1.set_placeIsEmpty(false)
                            strong_name.get_imageView().rotation = 180f
                        } else if (place_2.get_placeIsEmpty() == true) {
                            strong_name.set_placeCount_x(place_2.get_placeView().x)
                            strong_name.set_placeCount_y(place_2.get_placeView().y)
                            place_2.get_placeView().visibility = View.INVISIBLE
                            strong_name.set_nameDestination_x(0f)
                            strong_name.set_nameDestination_y(10f)
                            strong_name.set_name_textDestination_x(0f)
                            strong_name.set_name_textDestination_y(0f)
                            strong_name.set_count(4)
                            place_2.set_placeText("strong")
                            place_2.set_placeIsEmpty(false)
                        } else if (place_3.get_placeIsEmpty() == true) {
                            strong_name.set_placeCount_x(place_3.get_placeView().x)
                            strong_name.set_placeCount_y(place_3.get_placeView().y)
                            place_3.get_placeView().visibility = View.INVISIBLE
                            strong_name.set_nameDestination_x(0f)
                            strong_name.set_nameDestination_y(-10f)
                            strong_name.set_name_textDestination_x(0f)
                            strong_name.set_name_textDestination_y(0f)
                            strong_name.set_count(3)
                            place_3.set_placeText("strong")
                            place_3.set_placeIsEmpty(false)
                            strong_name.get_imageView().rotation = 180f
                        } else if (place_4.get_placeIsEmpty() == true) {
                            strong_name.set_placeCount_x(place_4.get_placeView().x)
                            strong_name.set_placeCount_y(place_4.get_placeView().y)
                            place_4.get_placeView().visibility = View.INVISIBLE
                            strong_name.set_nameDestination_x(0f)
                            strong_name.set_nameDestination_y(10f)
                            strong_name.set_name_textDestination_x(0f)
                            strong_name.set_name_textDestination_y(0f)
                            strong_name.set_count(2)
                            place_4.set_placeText("strong")
                            place_4.set_placeIsEmpty(false)
                        } else if (place_5.get_placeIsEmpty() == true) {
                            strong_name.set_placeCount_x(place_5.get_placeView().x)
                            strong_name.set_placeCount_y(place_5.get_placeView().y)
                            place_5.get_placeView().visibility = View.INVISIBLE
                            strong_name.set_nameDestination_x(0f)
                            strong_name.set_nameDestination_y(-10f)
                            strong_name.set_name_textDestination_x(0f)
                            strong_name.set_name_textDestination_y(0f)
                            strong_name.set_count(1)
                            place_5.set_placeText("strong")
                            place_5.set_placeIsEmpty(false)
                            strong_name.get_imageView().rotation = 180f
                        }
                        strong_name.set_isNameRunning(true)
                        strong_name.get_constraintLayout().x =
                            strong_name.get_placeCount_x() + strong_name.get_nameDestination_x()
                        strong_name.get_constraintLayout().y =
                            strong_name.get_placeCount_y() + strong_name.get_nameDestination_y()
                        strong_name.get_textView().x += strong_name.get_name_textDestination_x()
                        strong_name.get_textView().y += strong_name.get_name_textDestination_y()
                        strong_name.get_constraintLayout().startAnimation(fadeInAnimation)
                        if (place_1.get_placeIsEmpty() == false && place_2.get_placeIsEmpty() == false && place_3.get_placeIsEmpty() == false && place_4.get_placeIsEmpty() == false && place_5.get_placeIsEmpty() == false) {
                            var nodeList =
                                quotesSearching.quotes_searching(getXmlDocument("quotes.xml")!!)
                            quotes_author.setText(nodeList[0])
                            quotes_text.setText(nodeList[1])
                        }
                    }

                    override fun onAnimationRepeat(animation: Animation) {}
                })
            }
        }
            else if(is_helping==true){
                quotes_text.text = "Бу төймәгә бассагыз, көчле дигән мәгънәне белдергән исемнәр чыга"
                quotes_text.startAnimation(egg_scale)
            }
        }

        smart_name.get_imageView().setOnClickListener {
            if(is_helping==false){
            if (smart_name.get_isNameRunning() == false) {
                smart_name.get_constraintLayout().startAnimation(fadeOutAnimation)
                fadeOutAnimation.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation) {}
                    override fun onAnimationEnd(animation: Animation) {
                        if (place_1.get_placeIsEmpty() == true) {
                            smart_name.set_placeCount_x(place_1.get_placeView().x)
                            smart_name.set_placeCount_y(place_1.get_placeView().y)
                            place_1.get_placeView().visibility = View.INVISIBLE
                            smart_name.set_nameDestination_x(-7f)
                            smart_name.set_nameDestination_y(10f)
                            smart_name.set_name_textDestination_x(0f)
                            smart_name.set_name_textDestination_y(0f)
                            smart_name.set_count(5)
                            place_1.set_placeText("smart")
                            place_1.set_placeIsEmpty(false)
                        } else if (place_2.get_placeIsEmpty() == true) {
                            smart_name.set_placeCount_x(place_2.get_placeView().x)
                            smart_name.set_placeCount_y(place_2.get_placeView().y)
                            place_2.get_placeView().visibility = View.INVISIBLE
                            smart_name.set_nameDestination_x(-9f)
                            smart_name.set_nameDestination_y(-10f)
                            smart_name.set_name_textDestination_x(0f)
                            smart_name.set_name_textDestination_y(70f)
                            smart_name.set_count(4)
                            place_2.set_placeText("smart")
                            place_2.set_placeIsEmpty(false)
                            smart_name.get_imageView().rotation = 180f
                        } else if (place_3.get_placeIsEmpty() == true) {
                            smart_name.set_placeCount_x(place_3.get_placeView().x)
                            smart_name.set_placeCount_y(place_3.get_placeView().y)
                            place_3.get_placeView().visibility = View.INVISIBLE
                            smart_name.set_nameDestination_x(7f)
                            smart_name.set_nameDestination_y(10f)
                            smart_name.set_name_textDestination_x(0f)
                            smart_name.set_name_textDestination_y(0f)
                            smart_name.set_count(3)
                            place_3.set_placeText("smart")
                            place_3.set_placeIsEmpty(false)
                        } else if (place_4.get_placeIsEmpty() == true) {
                            smart_name.set_placeCount_x(place_4.get_placeView().x)
                            smart_name.set_placeCount_y(place_4.get_placeView().y)
                            place_4.get_placeView().visibility = View.INVISIBLE
                            smart_name.set_nameDestination_x(-7f)
                            smart_name.set_nameDestination_y(-10f)
                            smart_name.set_name_textDestination_x(0f)
                            smart_name.set_name_textDestination_y(70f)
                            smart_name.set_count(2)
                            smart_name.get_imageView().rotation = 180f
                            place_4.set_placeText("smart")
                            place_4.set_placeIsEmpty(false)
                        } else if (place_5.get_placeIsEmpty() == true) {
                            smart_name.set_placeCount_x(place_5.get_placeView().x)
                            smart_name.set_placeCount_y(place_5.get_placeView().y)
                            place_5.get_placeView().visibility = View.INVISIBLE
                            smart_name.set_nameDestination_x(-7f)
                            smart_name.set_nameDestination_y(10f)
                            smart_name.set_name_textDestination_x(0f)
                            smart_name.set_name_textDestination_y(0f)
                            smart_name.set_count(1)
                            place_5.set_placeText("smart")
                            place_5.set_placeIsEmpty(false)
                        }
                        smart_name.set_isNameRunning(true)
                        smart_name.get_constraintLayout().x =
                            smart_name.get_placeCount_x() + smart_name.get_nameDestination_x()
                        smart_name.get_constraintLayout().y =
                            smart_name.get_placeCount_y() + smart_name.get_nameDestination_y()
                        smart_name.get_textView().x += smart_name.get_name_textDestination_x()
                        smart_name.get_textView().y += smart_name.get_name_textDestination_y()
                        smart_name.get_constraintLayout().startAnimation(fadeInAnimation)
                        if (place_1.get_placeIsEmpty() == false && place_2.get_placeIsEmpty() == false && place_3.get_placeIsEmpty() == false && place_4.get_placeIsEmpty() == false && place_5.get_placeIsEmpty() == false) {
                            var nodeList =
                                quotesSearching.quotes_searching(getXmlDocument("quotes.xml")!!)
                            quotes_author.setText(nodeList[0])
                            quotes_text.setText(nodeList[1])
                        }
                    }

                    override fun onAnimationRepeat(animation: Animation) {}
                })
            }
        }
            else if(is_helping==true){
                quotes_text.text = "Бу төймәгә бассагыз, акыллы дигән мәгънәне белдергән исемнәр чыга"
                quotes_text.startAnimation(egg_scale)
            }
        }
        beautiful_name.get_imageView().setOnClickListener {
            if(is_helping==false){
            if (beautiful_name.get_isNameRunning() == false) {
                beautiful_name.get_constraintLayout().startAnimation(fadeOutAnimation)
                fadeOutAnimation.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation) {}
                    override fun onAnimationEnd(animation: Animation) {
                        if (place_1.get_placeIsEmpty() == true) {
                            beautiful_name.set_placeCount_x(place_1.get_placeView().x)
                            beautiful_name.set_placeCount_y(place_1.get_placeView().y)
                            place_1.get_placeView().visibility = View.INVISIBLE
                            beautiful_name.set_nameDestination_x(0f)
                            beautiful_name.set_nameDestination_y(-10f)
                            beautiful_name.set_name_textDestination_x(0f)
                            beautiful_name.set_name_textDestination_y(0f)
                            beautiful_name.set_count(5)
                            place_1.set_placeText("beautiful")
                            place_1.set_placeIsEmpty(false)
                            beautiful_name.get_imageView().rotation = 180f
                        } else if (place_2.get_placeIsEmpty() == true) {
                            beautiful_name.set_placeCount_x(place_2.get_placeView().x)
                            beautiful_name.set_placeCount_y(place_2.get_placeView().y)
                            place_2.get_placeView().visibility = View.INVISIBLE
                            beautiful_name.set_nameDestination_x(0f)
                            beautiful_name.set_nameDestination_y(10f)
                            beautiful_name.set_name_textDestination_x(0f)
                            beautiful_name.set_name_textDestination_y(0f)
                            beautiful_name.set_count(4)
                            place_2.set_placeText("beautiful")
                            place_2.set_placeIsEmpty(false)
                        } else if (place_3.get_placeIsEmpty() == true) {
                            beautiful_name.set_placeCount_x(place_3.get_placeView().x)
                            beautiful_name.set_placeCount_y(place_3.get_placeView().y)
                            place_3.get_placeView().visibility = View.INVISIBLE
                            beautiful_name.set_nameDestination_x(0f)
                            beautiful_name.set_nameDestination_y(-10f)
                            beautiful_name.set_name_textDestination_x(0f)
                            beautiful_name.set_name_textDestination_y(0f)
                            beautiful_name.set_count(3)
                            place_3.set_placeText("beautiful")
                            place_3.set_placeIsEmpty(false)
                            beautiful_name.get_imageView().rotation = 180f
                        } else if (place_4.get_placeIsEmpty() == true) {
                            beautiful_name.set_placeCount_x(place_4.get_placeView().x)
                            beautiful_name.set_placeCount_y(place_4.get_placeView().y)
                            place_4.get_placeView().visibility = View.INVISIBLE
                            beautiful_name.set_nameDestination_x(0f)
                            beautiful_name.set_nameDestination_y(10f)
                            beautiful_name.set_name_textDestination_x(0f)
                            beautiful_name.set_name_textDestination_y(0f)
                            beautiful_name.set_count(2)
                            place_4.set_placeText("beautiful")
                            place_4.set_placeIsEmpty(false)
                        } else if (place_5.get_placeIsEmpty() == true) {
                            beautiful_name.set_placeCount_x(place_5.get_placeView().x)
                            beautiful_name.set_placeCount_y(place_5.get_placeView().y)
                            place_5.get_placeView().visibility = View.INVISIBLE
                            beautiful_name.set_nameDestination_x(0f)
                            beautiful_name.set_nameDestination_y(-10f)
                            beautiful_name.set_name_textDestination_x(0f)
                            beautiful_name.set_name_textDestination_y(0f)
                            beautiful_name.set_count(1)
                            place_5.set_placeText("beautiful")
                            place_5.set_placeIsEmpty(false)
                            beautiful_name.get_imageView().rotation = 180f
                        }
                        beautiful_name.set_isNameRunning(true)
                        beautiful_name.get_constraintLayout().x =
                            beautiful_name.get_placeCount_x() + beautiful_name.get_nameDestination_x()
                        beautiful_name.get_constraintLayout().y =
                            beautiful_name.get_placeCount_y() + beautiful_name.get_nameDestination_y()
                        beautiful_name.get_textView().x += beautiful_name.get_name_textDestination_x()
                        beautiful_name.get_textView().y += beautiful_name.get_name_textDestination_y()
                        beautiful_name.get_constraintLayout().startAnimation(fadeInAnimation)
                        if (place_1.get_placeIsEmpty() == false && place_2.get_placeIsEmpty() == false && place_3.get_placeIsEmpty() == false && place_4.get_placeIsEmpty() == false && place_5.get_placeIsEmpty() == false) {
                            var nodeList =
                                quotesSearching.quotes_searching(getXmlDocument("quotes.xml")!!)
                            quotes_author.setText(nodeList[0])
                            quotes_text.setText(nodeList[1])
                        }
                    }

                    override fun onAnimationRepeat(animation: Animation) {}
                })
            }
        }
            else if(is_helping==true){
                quotes_text.text = "Бу төймәгә бассагыз, матур дигән мәгънәне белдергән исемнәр чыга"
                quotes_text.startAnimation(egg_scale)
            }
        }
        believer_name.get_imageView().setOnClickListener {
            if(is_helping==false) {
                if (believer_name.get_isNameRunning() == false) {
                    believer_name.get_constraintLayout().startAnimation(fadeOutAnimation)
                    fadeOutAnimation.setAnimationListener(object : Animation.AnimationListener {
                        override fun onAnimationStart(animation: Animation) {}
                        override fun onAnimationEnd(animation: Animation) {
                            if (place_1.get_placeIsEmpty() == true) {
                                believer_name.set_placeCount_x(place_1.get_placeView().x)
                                believer_name.set_placeCount_y(place_1.get_placeView().y)
                                place_1.get_placeView().visibility = View.INVISIBLE
                                believer_name.set_nameDestination_x(-7f)
                                believer_name.set_nameDestination_y(10f)
                                believer_name.set_name_textDestination_x(0f)
                                believer_name.set_name_textDestination_y(0f)
                                believer_name.set_count(5)
                                place_1.set_placeText("believer")
                                place_1.set_placeIsEmpty(false)
                            } else if (place_2.get_placeIsEmpty() == true) {
                                believer_name.set_placeCount_x(place_2.get_placeView().x)
                                believer_name.set_placeCount_y(place_2.get_placeView().y)
                                place_2.get_placeView().visibility = View.INVISIBLE
                                believer_name.set_nameDestination_x(-9f)
                                believer_name.set_nameDestination_y(-10f)
                                believer_name.set_name_textDestination_x(0f)
                                believer_name.set_name_textDestination_y(70f)
                                believer_name.set_count(4)
                                place_2.set_placeText("believer")
                                place_2.set_placeIsEmpty(false)
                                believer_name.get_imageView().rotation = 180f
                            } else if (place_3.get_placeIsEmpty() == true) {
                                believer_name.set_placeCount_x(place_3.get_placeView().x)
                                believer_name.set_placeCount_y(place_3.get_placeView().y)
                                place_3.get_placeView().visibility = View.INVISIBLE
                                believer_name.set_nameDestination_x(7f)
                                believer_name.set_nameDestination_y(10f)
                                believer_name.set_name_textDestination_x(0f)
                                believer_name.set_name_textDestination_y(0f)
                                believer_name.set_count(3)
                                place_3.set_placeText("believer")
                                place_3.set_placeIsEmpty(false)
                            } else if (place_4.get_placeIsEmpty() == true) {
                                believer_name.set_placeCount_x(place_4.get_placeView().x)
                                believer_name.set_placeCount_y(place_4.get_placeView().y)
                                place_4.get_placeView().visibility = View.INVISIBLE
                                believer_name.set_nameDestination_x(-7f)
                                believer_name.set_nameDestination_y(-10f)
                                believer_name.set_name_textDestination_x(0f)
                                believer_name.set_name_textDestination_y(70f)
                                believer_name.set_count(2)
                                believer_name.get_imageView().rotation = 180f
                                place_4.set_placeText("believer")
                                place_4.set_placeIsEmpty(false)
                            } else if (place_5.get_placeIsEmpty() == true) {
                                believer_name.set_placeCount_x(place_5.get_placeView().x)
                                believer_name.set_placeCount_y(place_5.get_placeView().y)
                                place_5.get_placeView().visibility = View.INVISIBLE
                                believer_name.set_nameDestination_x(-7f)
                                believer_name.set_nameDestination_y(10f)
                                believer_name.set_name_textDestination_x(0f)
                                believer_name.set_name_textDestination_y(0f)
                                believer_name.set_count(1)
                                place_5.set_placeText("believer")
                                place_5.set_placeIsEmpty(false)
                            }
                            believer_name.set_isNameRunning(true)
                            believer_name.get_constraintLayout().x =
                                believer_name.get_placeCount_x() + believer_name.get_nameDestination_x()
                            believer_name.get_constraintLayout().y =
                                believer_name.get_placeCount_y() + believer_name.get_nameDestination_y()
                            believer_name.get_textView().x += believer_name.get_name_textDestination_x()
                            believer_name.get_textView().y += believer_name.get_name_textDestination_y()
                            believer_name.get_constraintLayout().startAnimation(fadeInAnimation)
                            if (place_1.get_placeIsEmpty() == false && place_2.get_placeIsEmpty() == false && place_3.get_placeIsEmpty() == false && place_4.get_placeIsEmpty() == false && place_5.get_placeIsEmpty() == false) {
                                var nodeList =
                                    quotesSearching.quotes_searching(getXmlDocument("quotes.xml")!!)
                                quotes_author.setText(nodeList[0])
                                quotes_text.setText(nodeList[1])
                            }
                        }

                        override fun onAnimationRepeat(animation: Animation) {}
                    })
                }
            }
            else if(is_helping==true){
                quotes_text.text = "Бу төймәгә бассагыз, иманлы дигән мәгънәне белдергән исемнәр чыга"
                quotes_text.startAnimation(egg_scale)
            }
        }
        rich_name.get_imageView().setOnLongClickListener {
            if(rich_name.get_isNameRunning()==true){
                rich_name.get_constraintLayout().startAnimation(fadeOutAnimation)
                fadeOutAnimation.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation) {}
                    override fun onAnimationEnd(animation: Animation) {
                        if(place_1.get_placeIsEmpty()==false && place_1.get_placeText().equals("rich")){
                            place_1.get_placeView().visibility= View.VISIBLE
                            place_1.set_placeText("")
                            place_1.set_placeIsEmpty(true)
                        }
                        else if (place_2.get_placeIsEmpty()==false && place_2.get_placeText().equals("rich")){
                            place_2.get_placeView().visibility= View.VISIBLE
                            place_2.set_placeText("")
                            place_2.set_placeIsEmpty(true)
                        }
                        else if (place_3.get_placeIsEmpty()==false && place_3.get_placeText().equals("rich")){
                            place_3.get_placeView().visibility= View.VISIBLE
                            place_3.set_placeText("")
                            place_3.set_placeIsEmpty(true)
                        }
                        else if (place_4.get_placeIsEmpty()==false && place_4.get_placeText().equals("rich")){
                            place_4.get_placeView().visibility= View.VISIBLE
                            place_4.set_placeText("")
                            place_4.set_placeIsEmpty(true)
                        }
                        else if (place_5.get_placeIsEmpty()==false && place_5.get_placeText().equals("rich")){
                            place_5.get_placeView().visibility= View.VISIBLE
                            place_5.set_placeText("")
                            place_5.set_placeIsEmpty(true)
                        }
                        rich_name.set_count(0)
                        rich_name.get_constraintLayout().x = findViewById<TextView>(R.id.start_rich_destination).x
                        rich_name.get_constraintLayout().y = findViewById<TextView>(R.id.start_rich_destination).y
                        rich_name.get_textView().x-=rich_name.get_name_textDestination_x()
                        rich_name.get_textView().y-=rich_name.get_name_textDestination_y()
                        rich_name.get_imageView().rotation = 0f
                        rich_name.set_isNameRunning(false)
                        rich_name.get_constraintLayout().startAnimation(fadeInAnimation)
                        quotes_text.setText("")
                        quotes_author.setText("")
                    }
                    override fun onAnimationRepeat(animation: Animation) {}
                })
            }
                true
        }
        strong_name.get_imageView().setOnLongClickListener {
            if(strong_name.get_isNameRunning()==true){
                strong_name.get_constraintLayout().startAnimation(fadeOutAnimation)
                fadeOutAnimation.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation) {}
                    override fun onAnimationEnd(animation: Animation) {
                        if(place_1.get_placeIsEmpty()==false && place_1.get_placeText().equals("strong")){
                            place_1.get_placeView().visibility= View.VISIBLE
                            place_1.set_placeText("")
                            place_1.set_placeIsEmpty(true)
                        }
                        else if (place_2.get_placeIsEmpty()==false && place_2.get_placeText().equals("strong")){
                            place_2.get_placeView().visibility= View.VISIBLE
                            place_2.set_placeText("")
                            place_2.set_placeIsEmpty(true)
                        }
                        else if (place_3.get_placeIsEmpty()==false && place_3.get_placeText().equals("strong")){
                            place_3.get_placeView().visibility= View.VISIBLE
                            place_3.set_placeText("")
                            place_3.set_placeIsEmpty(true)
                        }
                        else if (place_4.get_placeIsEmpty()==false && place_4.get_placeText().equals("strong")){
                            place_4.get_placeView().visibility= View.VISIBLE
                            place_4.set_placeText("")
                            place_4.set_placeIsEmpty(true)
                        }
                        else if (place_5.get_placeIsEmpty()==false && place_5.get_placeText().equals("strong")){
                            place_5.get_placeView().visibility= View.VISIBLE
                            place_5.set_placeText("")
                            place_5.set_placeIsEmpty(true)
                        }
                        strong_name.set_count(0)
                        strong_name.get_constraintLayout().x = findViewById<TextView>(R.id.start_strong_destination).x
                        strong_name.get_constraintLayout().y = findViewById<TextView>(R.id.start_strong_destination).y
                        strong_name.get_textView().x-=strong_name.get_name_textDestination_x()
                        strong_name.get_textView().y-=strong_name.get_name_textDestination_y()
                        strong_name.get_imageView().rotation = 0f
                        strong_name.set_isNameRunning(false)
                        strong_name.get_constraintLayout().startAnimation(fadeInAnimation)
                        quotes_text.setText("")
                        quotes_author.setText("")
                    }
                    override fun onAnimationRepeat(animation: Animation) {}
                })
            }
            true
        }

        smart_name.get_imageView().setOnLongClickListener {
            if(smart_name.get_isNameRunning()==true){
                smart_name.get_constraintLayout().startAnimation(fadeOutAnimation)
                fadeOutAnimation.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation) {}
                    override fun onAnimationEnd(animation: Animation) {
                        if(place_1.get_placeIsEmpty()==false && place_1.get_placeText().equals("smart")){
                            place_1.get_placeView().visibility= View.VISIBLE
                            place_1.set_placeText("")
                            place_1.set_placeIsEmpty(true)
                        }
                        else if (place_2.get_placeIsEmpty()==false && place_2.get_placeText().equals("smart")){
                            place_2.get_placeView().visibility= View.VISIBLE
                            place_2.set_placeText("")
                            place_2.set_placeIsEmpty(true)
                        }
                        else if (place_3.get_placeIsEmpty()==false && place_3.get_placeText().equals("smart")){
                            place_3.get_placeView().visibility= View.VISIBLE
                            place_3.set_placeText("")
                            place_3.set_placeIsEmpty(true)
                        }
                        else if (place_4.get_placeIsEmpty()==false && place_4.get_placeText().equals("smart")){
                            place_4.get_placeView().visibility= View.VISIBLE
                            place_4.set_placeText("")
                            place_4.set_placeIsEmpty(true)
                        }
                        else if (place_5.get_placeIsEmpty()==false && place_5.get_placeText().equals("smart")){
                            place_5.get_placeView().visibility= View.VISIBLE
                            place_5.set_placeText("")
                            place_5.set_placeIsEmpty(true)
                        }
                        smart_name.set_count(0)
                        smart_name.get_constraintLayout().x = findViewById<TextView>(R.id.start_smart_destination).x
                        smart_name.get_constraintLayout().y = findViewById<TextView>(R.id.start_smart_destination).y
                        smart_name.get_textView().x-=smart_name.get_name_textDestination_x()
                        smart_name.get_textView().y-=smart_name.get_name_textDestination_y()
                        smart_name.get_imageView().rotation = 0f
                        smart_name.set_isNameRunning(false)
                        smart_name.get_constraintLayout().startAnimation(fadeInAnimation)
                        quotes_text.setText("")
                        quotes_author.setText("")
                    }
                    override fun onAnimationRepeat(animation: Animation) {}
                })
            }
            true
        }

        beautiful_name.get_imageView().setOnLongClickListener {
            if(beautiful_name.get_isNameRunning()==true){
                beautiful_name.get_constraintLayout().startAnimation(fadeOutAnimation)
                fadeOutAnimation.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation) {}
                    override fun onAnimationEnd(animation: Animation) {
                        if(place_1.get_placeIsEmpty()==false && place_1.get_placeText().equals("beautiful")){
                            place_1.get_placeView().visibility= View.VISIBLE
                            place_1.set_placeText("")
                            place_1.set_placeIsEmpty(true)
                        }
                        else if (place_2.get_placeIsEmpty()==false && place_2.get_placeText().equals("beautiful")){
                            place_2.get_placeView().visibility= View.VISIBLE
                            place_2.set_placeText("")
                            place_2.set_placeIsEmpty(true)
                        }
                        else if (place_3.get_placeIsEmpty()==false && place_3.get_placeText().equals("beautiful")){
                            place_3.get_placeView().visibility= View.VISIBLE
                            place_3.set_placeText("")
                            place_3.set_placeIsEmpty(true)
                        }
                        else if (place_4.get_placeIsEmpty()==false && place_4.get_placeText().equals("beautiful")){
                            place_4.get_placeView().visibility= View.VISIBLE
                            place_4.set_placeText("")
                            place_4.set_placeIsEmpty(true)
                        }
                        else if (place_5.get_placeIsEmpty()==false && place_5.get_placeText().equals("beautiful")){
                            place_5.get_placeView().visibility= View.VISIBLE
                            place_5.set_placeText("")
                            place_5.set_placeIsEmpty(true)
                        }
                        beautiful_name.set_count(0)
                        beautiful_name.get_constraintLayout().x = findViewById<TextView>(R.id.start_beautiful_destination).x
                        beautiful_name.get_constraintLayout().y = findViewById<TextView>(R.id.start_beautiful_destination).y
                        beautiful_name.get_textView().x-=beautiful_name.get_name_textDestination_x()
                        beautiful_name.get_textView().y-=beautiful_name.get_name_textDestination_y()
                        beautiful_name.get_imageView().rotation = 0f
                        beautiful_name.set_isNameRunning(false)
                        beautiful_name.get_constraintLayout().startAnimation(fadeInAnimation)
                        quotes_text.setText("")
                        quotes_author.setText("")
                    }
                    override fun onAnimationRepeat(animation: Animation) {}
                })
            }
            true
        }
        believer_name.get_imageView().setOnLongClickListener {
            if(believer_name.get_isNameRunning()==true){
                believer_name.get_constraintLayout().startAnimation(fadeOutAnimation)
                fadeOutAnimation.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation) {}
                    override fun onAnimationEnd(animation: Animation) {
                        if(place_1.get_placeIsEmpty()==false && place_1.get_placeText().equals("believer")){
                            place_1.get_placeView().visibility= View.VISIBLE
                            place_1.set_placeText("")
                            place_1.set_placeIsEmpty(true)
                        }
                        else if (place_2.get_placeIsEmpty()==false && place_2.get_placeText().equals("believer")){
                            place_2.get_placeView().visibility= View.VISIBLE
                            place_2.set_placeText("")
                            place_2.set_placeIsEmpty(true)
                        }
                        else if (place_3.get_placeIsEmpty()==false && place_3.get_placeText().equals("believer")){
                            place_3.get_placeView().visibility= View.VISIBLE
                            place_3.set_placeText("")
                            place_3.set_placeIsEmpty(true)
                        }
                        else if (place_4.get_placeIsEmpty()==false && place_4.get_placeText().equals("believer")){
                            place_4.get_placeView().visibility= View.VISIBLE
                            place_4.set_placeText("")
                            place_4.set_placeIsEmpty(true)
                        }
                        else if (place_5.get_placeIsEmpty()==false && place_5.get_placeText().equals("believer")){
                            place_5.get_placeView().visibility= View.VISIBLE
                            place_5.set_placeText("")
                            place_5.set_placeIsEmpty(true)
                        }
                        believer_name.set_count(0)
                        believer_name.get_constraintLayout().x = findViewById<TextView>(R.id.start_believer_destination).x
                        believer_name.get_constraintLayout().y = findViewById<TextView>(R.id.start_believer_destination).y
                        believer_name.get_textView().x-=believer_name.get_name_textDestination_x()
                        believer_name.get_textView().y-=believer_name.get_name_textDestination_y()
                        believer_name.get_imageView().rotation = 0f
                        believer_name.set_isNameRunning(false)
                        believer_name.get_constraintLayout().startAnimation(fadeInAnimation)
                        quotes_text.setText("")
                        quotes_author.setText("")
                    }
                    override fun onAnimationRepeat(animation: Animation) {}
                })
            }
            true
        }
        celebrity_person1.get_infoView().setOnClickListener {
            if (is_helping == false) {
                if (is_celebrity_meaning1 == false) {
                    celebrity_person1.get_textView().setTextSize(14f)
                    celebrity_person1.get_textView().setText(celebrity_meaning1)
                    is_celebrity_meaning1 = true
                } else {
                    celebrity_person1.get_textView().setTextSize(16f)
                    celebrity_person1.get_textView().setText(celebrity_name1)
                    is_celebrity_meaning1 = false
                }
            }
            else {
                quotes_text.text = "Бу төймәгә бассагыз, кешенең кем булганын белә аласыз"
                quotes_text.startAnimation(egg_scale)
            }
        }
        celebrity_person2.get_infoView().setOnClickListener {
            if (is_helping == false) {
                if (is_celebrity_meaning2 == false) {
                    celebrity_person2.get_textView().setTextSize(14f)
                    celebrity_person2.get_textView().setText(celebrity_meaning2)
                    is_celebrity_meaning2 = true
                } else {
                    celebrity_person2.get_textView().setTextSize(16f)
                    celebrity_person2.get_textView().setText(celebrity_name2)
                    is_celebrity_meaning2 = false
                }
            }
            else {
            quotes_text.text = "Бу төймәгә бассагыз, кешенең кем булганын белә аласыз"
            quotes_text.startAnimation(egg_scale)
        }
        }
        celebrity_person3.get_infoView().setOnClickListener {
            if (is_helping == false) {
                if (is_celebrity_meaning3 == false) {
                    celebrity_person3.get_textView().setTextSize(14f)
                    celebrity_person3.get_textView().setText(celebrity_meaning3)
                    is_celebrity_meaning3 = true
                } else {
                    celebrity_person3.get_textView().setTextSize(16f)
                    celebrity_person3.get_textView().setText(celebrity_name3)
                    is_celebrity_meaning3 = false
                }
            }
            else {
            quotes_text.text = "Бу төймәгә бассагыз, кешенең кем булганын белә аласыз"
            quotes_text.startAnimation(egg_scale)
        }
        }
        addListener(result,meaning,name,character_type, quotesSearching,
            place_1,place_2,place_3,place_4,place_5,quotes_author,quotes_text,
            celebrity_person1,celebrity_person2,celebrity_person3,
        rich_name, strong_name,smart_name,beautiful_name,believer_name)
    }
    open fun getXmlDocument(filename: String): Document? {
        return try {
            val dbFactory = DocumentBuilderFactory.newInstance()
            val dBuilder = dbFactory.newDocumentBuilder()
            val document = dBuilder.parse(assets.open(filename))
            document.documentElement.normalize()
            return document
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
    fun getValueForTag(document: Document?, tag: String?, index: Int): Int {
        val nodes = document!!.getElementsByTagName(tag)
        return nodes.item(index).textContent.toInt()
    }
    private fun addListener(root: View,root2: TextView,root3: TextView,root4: TextView,quotesSearching: Quotes_searching
                            ,place_1:Place,place_2:Place,place_3:Place,place_4:Place,place_5:Place, quotes_author: TextView, quotes_text:TextView,
                            celebrity_person1: Result_celebrity, celebrity_person2: Result_celebrity,celebrity_person3:  Result_celebrity,
   rich_name: Name, strong_name: Name, smart_name: Name, beautiful_name:Name,believer_name:Name ) {
            root.isClickable = true
            root.setOnLongClickListener { v ->
                mExplosionField!!.explode(v)
                root.visibility = View.INVISIBLE
                root2.setText("")
                root3.setText("")
                root4.setText("")
                celebrity_person1.get_imageView().visibility = View.INVISIBLE
                celebrity_person2.get_imageView().visibility = View.INVISIBLE
                celebrity_person3.get_imageView().visibility = View.INVISIBLE
                celebrity_person1.get_infoView().visibility = View.INVISIBLE
                celebrity_person2.get_infoView().visibility = View.INVISIBLE
                celebrity_person3.get_infoView().visibility = View.INVISIBLE
                celebrity_person1.get_textView().setText("")
                celebrity_person2.get_textView().setText("")
                celebrity_person3.get_textView().setText("")
                quotes_author.setText("")
                quotes_text.setText("")
                rich_name.get_imageView().visibility = View.VISIBLE
                rich_name.get_textView().visibility = View.VISIBLE
                strong_name.get_imageView().visibility = View.VISIBLE
                strong_name.get_textView().visibility = View.VISIBLE
                smart_name.get_imageView().visibility = View.VISIBLE
                smart_name.get_textView().visibility = View.VISIBLE
                beautiful_name.get_imageView().visibility = View.VISIBLE
                beautiful_name.get_textView().visibility = View.VISIBLE
                believer_name.get_imageView().visibility = View.VISIBLE
                believer_name.get_textView().visibility = View.VISIBLE
                if (place_1.get_placeIsEmpty()==false && place_2.get_placeIsEmpty()==false && place_3.get_placeIsEmpty()==false && place_4.get_placeIsEmpty()==false && place_5.get_placeIsEmpty()==false){
                    var nodeList = quotesSearching.quotes_searching(getXmlDocument("quotes.xml")!!)
                    quotes_author.setText(nodeList[0])
                    quotes_text.setText(nodeList[1])
                }
                true
            }
        }
    }
