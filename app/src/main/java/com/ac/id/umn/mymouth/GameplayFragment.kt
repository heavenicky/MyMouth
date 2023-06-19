package com.ac.id.umn.mymouth

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.fragment_gameplay.*
import kotlinx.android.synthetic.main.fragment_leaderboard.*
import kotlinx.android.synthetic.main.fragment_profile.*


class GameplayFragment : Fragment() {

    var currentNo = 0
    var currentscore = 0
    var totalcoin = 0
    var totalscore = 0
    var trueanswer = ""
    var levellist = ""
    var learn = 0

    private lateinit var timer: CountDownTimer
    private lateinit var mAuth: FirebaseAuth
    private lateinit var database: FirebaseFirestore
    private lateinit var navController: NavController

    var achiev = ""

    var lvl1score = 0
    var lvl2score = 0
    var lvl3score = 0
    var lvl4score = 0
    var lvl5score = 0
    var lvl6score = 0
    var lvl7score = 0
    var lvl8score = 0
    var lvl9score = 0
    var lvl10score = 0

    var listAchiev:ArrayList<Int> = ArrayList()

    var listData:ArrayList<gameplay> = ArrayList()

    var listLevel:ArrayList<String> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gameplay, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAuth = FirebaseAuth.getInstance()
        navController = Navigation.findNavController(view)
        database = FirebaseFirestore.getInstance()

        database.collection("ms_user").document(mAuth.uid.toString())
            .get().addOnSuccessListener {
                 totalcoin = it.getLong("money")!!.toInt()
                 totalscore = it.getLong("score")!!.toInt()
                    levellist = it.get("level").toString()
                    achiev = it.get("achievementList").toString()
                    lvl1score = it.getLong("level1score")!!.toInt()
                    lvl2score = it.getLong("level2score")!!.toInt()
                    lvl3score = it.getLong("level3score")!!.toInt()
                    lvl4score = it.getLong("level4score")!!.toInt()
                    lvl5score = it.getLong("level5score")!!.toInt()
                    lvl6score = it.getLong("level6score")!!.toInt()
                    lvl7score = it.getLong("level7score")!!.toInt()
                    lvl8score = it.getLong("level8score")!!.toInt()
                    lvl9score = it.getLong("level9score")!!.toInt()
                    lvl10score = it.getLong("level10score")!!.toInt()


                    if(achiev.contains("1")){
                        listAchiev.add(1)

                    }
                    if(achiev.contains("2")){
                        listAchiev.add(2)

                    }
                    if(achiev.contains("3")){
                        listAchiev.add(3)

                    }
                    if(achiev.contains("4")){
                        listAchiev.add(4)

                    }

            }

        learn = requireActivity().intent.getIntExtra("Status", 0)
        timer = object:CountDownTimer(60000,1000){
            override fun onTick(p0: Long) {
                txttimer.text = (p0/1000).toString()
            }

            override fun onFinish() {
                database.collection("ms_user").document(mAuth.uid.toString()).update("score",currentscore +  totalscore,
                    "money",currentscore +  totalcoin, "updatescore",currentscore)

                layouta.isClickable = false
                layoutb.isClickable = false
                layoutc.isClickable = false
                layoutd.isClickable = false

                Handler().postDelayed({
                    navController.navigate(R.id.action_gameplayFragment_to_endgameFragment)
                }, 600)

            }

        }
        if(learn == 1){

            listData.add(gameplay("Organ tubuh yang berfungsi sebagai alat pencernaan pertama kali adalah", "Mulut","Kerongkongan","Mulut"
            ,"Tenggorokan","Lambung"))
            listData.add(gameplay("Salah satu fungsi mulut adalah sebagai berikut, kecuali" ,
                    "Mencium",
                    "Berbicara",
                    "Mencerna Makanan",
                    "Bernafas",
                    "Mencium"))
            listData.add(gameplay("Total gigi yang biasanya dimiliki oleh manusia berjumlah" ,
                    "32",
                    "31",
                    "32",
                    "33",
                    "34"))
            listData.add(gameplay("Organ yang membantu menggerakkan makanan di sekitar mulut dan membantu kamu berbicara adalah" ,
                    "Lidah",
                    "Bibir",
                    "Gusi",
                    "Lidah",
                    "Gigi"))

            timer.start()
            nextquestion()
            txtnosoal.text =  "1/3"

            layouta.setOnClickListener {
               if(trueanswer == "A"){
                   currentscore = 100 + currentscore
                   txtscoregameplay.text = currentscore.toString()
                   layouta.setBackgroundResource(R.drawable.kotakbenar)

                   Handler().postDelayed({
                       layouta.setBackgroundResource(R.drawable.kotakputih)
                   }, 500)

                   nextquestion()
                   txtnosoal.text = currentNo.toString() + "/3"
               }
                else{
                   txtscoregameplay.text = currentscore.toString()
                   layouta.setBackgroundResource(R.drawable.kotaksalah)

                   Handler().postDelayed({
                       layouta.setBackgroundResource(R.drawable.kotakputih)
                   }, 500)

                   nextquestion()
                   txtnosoal.text = currentNo.toString() + "/3"
                }
            }

            layoutb.setOnClickListener {
                if(trueanswer == "B"){
                    currentscore = 100 + currentscore
                    txtscoregameplay.text = currentscore.toString()
                    layoutb.setBackgroundResource(R.drawable.kotakbenar)

                    Handler().postDelayed({
                        layoutb.setBackgroundResource(R.drawable.kotakputih)
                    }, 500)

                    nextquestion()
                    txtnosoal.text = currentNo.toString() + "/3"
                }
                else{
                    txtscoregameplay.text = currentscore.toString()
                    layoutb.setBackgroundResource(R.drawable.kotaksalah)

                    Handler().postDelayed({
                        layoutb.setBackgroundResource(R.drawable.kotakputih)
                    }, 500)

                    nextquestion()
                    txtnosoal.text = currentNo.toString() + "/3"
                }
            }

            layoutc.setOnClickListener {
                if(trueanswer == "C"){
                    currentscore = 100 + currentscore
                    txtscoregameplay.text = currentscore.toString()
                    layoutc.setBackgroundResource(R.drawable.kotakbenar)

                    Handler().postDelayed({
                        layoutc.setBackgroundResource(R.drawable.kotakputih)
                    }, 500)

                    nextquestion()
                    txtnosoal.text = currentNo.toString() + "/3"
                }
                else{
                    txtscoregameplay.text = currentscore.toString()
                    layoutc.setBackgroundResource(R.drawable.kotaksalah)

                    Handler().postDelayed({
                        layoutc.setBackgroundResource(R.drawable.kotakputih)
                    }, 500)

                    nextquestion()
                    txtnosoal.text = currentNo.toString() + "/3"
                }
            }

            layoutd.setOnClickListener {
                if(trueanswer == "D"){
                    currentscore = 100 + currentscore
                    txtscoregameplay.text = currentscore.toString()
                    layoutd.setBackgroundResource(R.drawable.kotakbenar)

                    Handler().postDelayed({
                        layoutd.setBackgroundResource(R.drawable.kotakputih)
                    }, 500)

                    nextquestion()
                    txtnosoal.text = currentNo.toString() + "/3"
                }
                else{
                    txtscoregameplay.text = currentscore.toString()
                    layoutd.setBackgroundResource(R.drawable.kotaksalah)

                    Handler().postDelayed({
                        layoutd.setBackgroundResource(R.drawable.kotakputih)
                    }, 500)

                    nextquestion()
                    txtnosoal.text = currentNo.toString() + "/3"
                }

            }
        }

        if(learn == 2){

            listData.add(gameplay("kerusakan gigi akibat infeksi bakteri yang merusak lapisan gigi sehingga merusak struktur gigi disebut" ,
                    "Karies gigi",
                    "Sariawan",
                    "Karies gigi",
                    "Infeksi gigi",
                    "Radang gusi"))
            listData.add(gameplay("Bakteri pada mulut mengubah gula yang terdapat dalam gigi menjadi" ,
                    "asam",
                    "plak gigi",
                    "enzim",
                    "glukosa",
                    "asam"))
            listData.add(gameplay("Berikut adalah cara - cara untuk menjaga kebersihan gigi, kecuali" ,
                    "Warna dari putih ke kuning dan coklat",
                    "Menyikat gigi minimal 2 kali sehari",
                    "membersihkan gigi dengan menggunakan benang gigi",
                    "berkumur dengan larutan garam dan air hangat",
                    "memakai kawat gigi"))
            listData.add(gameplay("Gigi berlubang dapat menyebabkan nyeri pada gigi jika sampai terlalu dalam kerusakannya karena telah sampai merusak  " ,
                    "Saraf gigi",
                    "Saraf gigi",
                    "Gigi",
                    "Gusi",
                    "Bibir"))

            timer.start()
            nextquestion()
            txtnosoal.text =  "1/3"

            layouta.setOnClickListener {
                if(trueanswer == "A"){
                    currentscore = 100 + currentscore
                    txtscoregameplay.text = currentscore.toString()
                    layouta.setBackgroundResource(R.drawable.kotakbenar)

                    Handler().postDelayed({
                        layouta.setBackgroundResource(R.drawable.kotakputih)
                    }, 500)

                    nextquestion()
                    txtnosoal.text = currentNo.toString() + "/3"
                }
                else{
                    txtscoregameplay.text = currentscore.toString()
                    layouta.setBackgroundResource(R.drawable.kotaksalah)

                    Handler().postDelayed({
                        layouta.setBackgroundResource(R.drawable.kotakputih)
                    }, 500)

                    nextquestion()
                    txtnosoal.text = currentNo.toString() + "/3"
                }
            }

            layoutb.setOnClickListener {
                if(trueanswer == "B"){
                    currentscore = 100 + currentscore
                    txtscoregameplay.text = currentscore.toString()
                    layoutb.setBackgroundResource(R.drawable.kotakbenar)

                    Handler().postDelayed({
                        layoutb.setBackgroundResource(R.drawable.kotakputih)
                    }, 500)

                    nextquestion()
                    txtnosoal.text = currentNo.toString() + "/3"
                }
                else{
                    txtscoregameplay.text = currentscore.toString()
                    layoutb.setBackgroundResource(R.drawable.kotaksalah)

                    Handler().postDelayed({
                        layoutb.setBackgroundResource(R.drawable.kotakputih)
                    }, 500)

                    nextquestion()
                    txtnosoal.text = currentNo.toString() + "/3"
                }
            }

            layoutc.setOnClickListener {
                if(trueanswer == "C"){
                    currentscore = 100 + currentscore
                    txtscoregameplay.text = currentscore.toString()
                    layoutc.setBackgroundResource(R.drawable.kotakbenar)

                    Handler().postDelayed({
                        layoutc.setBackgroundResource(R.drawable.kotakputih)
                    }, 500)

                    nextquestion()
                    txtnosoal.text = currentNo.toString() + "/3"
                }
                else{
                    txtscoregameplay.text = currentscore.toString()
                    layoutc.setBackgroundResource(R.drawable.kotaksalah)

                    Handler().postDelayed({
                        layoutc.setBackgroundResource(R.drawable.kotakputih)
                    }, 500)

                    nextquestion()
                    txtnosoal.text = currentNo.toString() + "/3"
                }
            }

            layoutd.setOnClickListener {
                if(trueanswer == "D"){
                    currentscore = 100 + currentscore
                    txtscoregameplay.text = currentscore.toString()
                    layoutd.setBackgroundResource(R.drawable.kotakbenar)

                    Handler().postDelayed({
                        layoutd.setBackgroundResource(R.drawable.kotakputih)
                    }, 500)

                    nextquestion()
                    txtnosoal.text = currentNo.toString() + "/3"
                }
                else{
                    txtscoregameplay.text = currentscore.toString()
                    layoutd.setBackgroundResource(R.drawable.kotaksalah)

                    Handler().postDelayed({
                        layoutd.setBackgroundResource(R.drawable.kotakputih)
                    }, 500)

                    nextquestion()
                    txtnosoal.text = currentNo.toString() + "/3"
                }

            }
        }

        if(learn == 3){

            listData.add(gameplay("Jaringan tubuh yang sangat keras dibanding yang lainnya" ,
                    "Gigi",
                    "Gigi",
                    "Tulang belakang",
                    "Kuku",
                    "Kepala"))
            listData.add(gameplay("Gigi susu yang terdapat pada anak - anak berjumlah " ,
                    "20",
                    "32",
                    "20",
                    "25",
                    "23"))
            listData.add(gameplay("Gigi dewasa yang terdapat pada orang dewasa berjumlah " ,
                    "32",
                    "32",
                    "20",
                    "25",
                    "23"))
            listData.add(gameplay("Total gigi geraham yang terdapat pada gigi dewasa adalah " ,
                    "20",
                    "20",
                    "8",
                    "12",
                    "5"))

            timer.start()
            nextquestion()
            txtnosoal.text =  "1/3"

            layouta.setOnClickListener {
                if(trueanswer == "A"){
                    currentscore = 100 + currentscore
                    txtscoregameplay.text = currentscore.toString()
                    layouta.setBackgroundResource(R.drawable.kotakbenar)

                    Handler().postDelayed({
                        layouta.setBackgroundResource(R.drawable.kotakputih)
                    }, 500)

                    nextquestion()
                    txtnosoal.text = currentNo.toString() + "/3"
                }
                else{
                    txtscoregameplay.text = currentscore.toString()
                    layouta.setBackgroundResource(R.drawable.kotaksalah)

                    Handler().postDelayed({
                        layouta.setBackgroundResource(R.drawable.kotakputih)
                    }, 500)

                    nextquestion()
                    txtnosoal.text = currentNo.toString() + "/3"
                }
            }

            layoutb.setOnClickListener {
                if(trueanswer == "B"){
                    currentscore = 100 + currentscore
                    txtscoregameplay.text = currentscore.toString()
                    layoutb.setBackgroundResource(R.drawable.kotakbenar)

                    Handler().postDelayed({
                        layoutb.setBackgroundResource(R.drawable.kotakputih)
                    }, 500)

                    nextquestion()
                    txtnosoal.text = currentNo.toString() + "/3"
                }
                else{
                    txtscoregameplay.text = currentscore.toString()
                    layoutb.setBackgroundResource(R.drawable.kotaksalah)

                    Handler().postDelayed({
                        layoutb.setBackgroundResource(R.drawable.kotakputih)
                    }, 500)

                    nextquestion()
                    txtnosoal.text = currentNo.toString() + "/3"
                }
            }

            layoutc.setOnClickListener {
                if(trueanswer == "C"){
                    currentscore = 100 + currentscore
                    txtscoregameplay.text = currentscore.toString()
                    layoutc.setBackgroundResource(R.drawable.kotakbenar)

                    Handler().postDelayed({
                        layoutc.setBackgroundResource(R.drawable.kotakputih)
                    }, 500)

                    nextquestion()
                    txtnosoal.text = currentNo.toString() + "/3"
                }
                else{
                    txtscoregameplay.text = currentscore.toString()
                    layoutc.setBackgroundResource(R.drawable.kotaksalah)

                    Handler().postDelayed({
                        layoutc.setBackgroundResource(R.drawable.kotakputih)
                    }, 500)

                    nextquestion()
                    txtnosoal.text = currentNo.toString() + "/3"
                }
            }

            layoutd.setOnClickListener {
                if(trueanswer == "D"){
                    currentscore = 100 + currentscore
                    txtscoregameplay.text = currentscore.toString()
                    layoutd.setBackgroundResource(R.drawable.kotakbenar)

                    Handler().postDelayed({
                        layoutd.setBackgroundResource(R.drawable.kotakputih)
                    }, 500)

                    nextquestion()
                    txtnosoal.text = currentNo.toString() + "/3"
                }
                else{
                    txtscoregameplay.text = currentscore.toString()
                    layoutd.setBackgroundResource(R.drawable.kotaksalah)

                    Handler().postDelayed({
                        layoutd.setBackgroundResource(R.drawable.kotakputih)
                    }, 500)

                    nextquestion()
                    txtnosoal.text = currentNo.toString() + "/3"
                }

            }
        }

        if(learn == 4){

            listData.add(gameplay("suatu penyimpangan dari bentuk normal akibat gangguan pada stadium pertumbuhan dan perkembangan gigi disebut" ,
                    "Anomali gigi",
                    "Anomali gigi",
                    "Gigi berlubang",
                    "Infeksi gigi",
                    "Radang gusi"))
            listData.add(gameplay("kondisi dimana keadaan benih gigi tidak ada yaitu" ,
                    "Anodontia Partialis",
                    "Dilaserasi",
                    "Anodontia Partialis",
                    "Mikrodonsia",
                    "Ankylosis"))
            listData.add(gameplay("akar dan mahkota gigi yang sangat bengkok / distorsi, sering membentuk sudut 45 derajat sampai lebih dari 90 derajat disebut dengan" ,
                    "Dilaserasi",
                    "Concrescence",
                    "Dilaserasi",
                    "Hipersementosis",
                    "Ankylosis"))
            listData.add(gameplay("gigi dengan ukuran lebih besar dari ukuran normal disebut  " ,
                    "Makrodonsia",
                    "Dilaserasi",
                    "Makrodonsia",
                    "Mikrodonsia",
                    "Flexion"))

            timer.start()
            nextquestion()
            txtnosoal.text =  "1/3"

            layouta.setOnClickListener {
                if(trueanswer == "A"){
                    currentscore = 100 + currentscore
                    txtscoregameplay.text = currentscore.toString()
                    layouta.setBackgroundResource(R.drawable.kotakbenar)

                    Handler().postDelayed({
                        layouta.setBackgroundResource(R.drawable.kotakputih)
                    }, 500)

                    nextquestion()
                    txtnosoal.text = currentNo.toString() + "/3"
                }
                else{
                    txtscoregameplay.text = currentscore.toString()
                    layouta.setBackgroundResource(R.drawable.kotaksalah)

                    Handler().postDelayed({
                        layouta.setBackgroundResource(R.drawable.kotakputih)
                    }, 500)

                    nextquestion()
                    txtnosoal.text = currentNo.toString() + "/3"
                }
            }

            layoutb.setOnClickListener {
                if(trueanswer == "B"){
                    currentscore = 100 + currentscore
                    txtscoregameplay.text = currentscore.toString()
                    layoutb.setBackgroundResource(R.drawable.kotakbenar)

                    Handler().postDelayed({
                        layoutb.setBackgroundResource(R.drawable.kotakputih)
                    }, 500)

                    nextquestion()
                    txtnosoal.text = currentNo.toString() + "/3"
                }
                else{
                    txtscoregameplay.text = currentscore.toString()
                    layoutb.setBackgroundResource(R.drawable.kotaksalah)

                    Handler().postDelayed({
                        layoutb.setBackgroundResource(R.drawable.kotakputih)
                    }, 500)

                    nextquestion()
                    txtnosoal.text = currentNo.toString() + "/3"
                }
            }

            layoutc.setOnClickListener {
                if(trueanswer == "C"){
                    currentscore = 100 + currentscore
                    txtscoregameplay.text = currentscore.toString()
                    layoutc.setBackgroundResource(R.drawable.kotakbenar)

                    Handler().postDelayed({
                        layoutc.setBackgroundResource(R.drawable.kotakputih)
                    }, 500)

                    nextquestion()
                    txtnosoal.text = currentNo.toString() + "/3"
                }
                else{
                    txtscoregameplay.text = currentscore.toString()
                    layoutc.setBackgroundResource(R.drawable.kotaksalah)

                    Handler().postDelayed({
                        layoutc.setBackgroundResource(R.drawable.kotakputih)
                    }, 500)

                    nextquestion()
                    txtnosoal.text = currentNo.toString() + "/3"
                }
            }

            layoutd.setOnClickListener {
                if(trueanswer == "D"){
                    currentscore = 100 + currentscore
                    txtscoregameplay.text = currentscore.toString()
                    layoutd.setBackgroundResource(R.drawable.kotakbenar)

                    Handler().postDelayed({
                        layoutd.setBackgroundResource(R.drawable.kotakputih)
                    }, 500)

                    nextquestion()
                    txtnosoal.text = currentNo.toString() + "/3"
                }
                else{
                    txtscoregameplay.text = currentscore.toString()
                    layoutd.setBackgroundResource(R.drawable.kotaksalah)

                    Handler().postDelayed({
                        layoutd.setBackgroundResource(R.drawable.kotakputih)
                    }, 500)

                    nextquestion()
                    txtnosoal.text = currentNo.toString() + "/3"
                }

            }
        }

        if(learn == 5){

            listData.add(gameplay("Karena kita punya bibir, kita bisa memiliki huruf" ,
                    "b, p, dan m",
                    "a, i, dan u ",
                    "b, p, dan m",
                    "s, k, dan p",
                    "r, L, dan z"))
            listData.add(gameplay("satu-satunya sumber pelembab bagi bibir adalah " ,
                    "air liur",
                    "air liur",
                    "kelenjar minyak",
                    "darah",
                    "keringat"))
            listData.add(gameplay("Bibir tidak mempunyai (.......) sehingga pembuluh darah kapiler dapat terlihat dan menyebabkan bibir berwarna merah." ,
                    "pigmen melanin",
                    "epidermis",
                    "stratum corneum",
                    "pigmen melanin",
                    "dermis"))
            listData.add(gameplay("sebagai tempat artikulasi, membantu menahan udara dari paru-paru sehingga dihasilkan bunyi-bunyi tertentu adalah fungsi dari" ,
                    "bibir",
                    "gigi",
                    "lidah",
                    "gusi",
                    "bibir"))

            timer.start()
            nextquestion()
            txtnosoal.text =  "1/3"

            layouta.setOnClickListener {
                if(trueanswer == "A"){
                    currentscore = 100 + currentscore
                    txtscoregameplay.text = currentscore.toString()
                    layouta.setBackgroundResource(R.drawable.kotakbenar)

                    Handler().postDelayed({
                        layouta.setBackgroundResource(R.drawable.kotakputih)
                    }, 500)

                    nextquestion()
                    txtnosoal.text = currentNo.toString() + "/3"
                }
                else{
                    txtscoregameplay.text = currentscore.toString()
                    layouta.setBackgroundResource(R.drawable.kotaksalah)

                    Handler().postDelayed({
                        layouta.setBackgroundResource(R.drawable.kotakputih)
                    }, 500)

                    nextquestion()
                    txtnosoal.text = currentNo.toString() + "/3"
                }
            }

            layoutb.setOnClickListener {
                if(trueanswer == "B"){
                    currentscore = 100 + currentscore
                    txtscoregameplay.text = currentscore.toString()
                    layoutb.setBackgroundResource(R.drawable.kotakbenar)

                    Handler().postDelayed({
                        layoutb.setBackgroundResource(R.drawable.kotakputih)
                    }, 500)

                    nextquestion()
                    txtnosoal.text = currentNo.toString() + "/3"
                }
                else{
                    txtscoregameplay.text = currentscore.toString()
                    layoutb.setBackgroundResource(R.drawable.kotaksalah)

                    Handler().postDelayed({
                        layoutb.setBackgroundResource(R.drawable.kotakputih)
                    }, 500)

                    nextquestion()
                    txtnosoal.text = currentNo.toString() + "/3"
                }
            }

            layoutc.setOnClickListener {
                if(trueanswer == "C"){
                    currentscore = 100 + currentscore
                    txtscoregameplay.text = currentscore.toString()
                    layoutc.setBackgroundResource(R.drawable.kotakbenar)

                    Handler().postDelayed({
                        layoutc.setBackgroundResource(R.drawable.kotakputih)
                    }, 500)

                    nextquestion()
                    txtnosoal.text = currentNo.toString() + "/3"
                }
                else{
                    txtscoregameplay.text = currentscore.toString()
                    layoutc.setBackgroundResource(R.drawable.kotaksalah)

                    Handler().postDelayed({
                        layoutc.setBackgroundResource(R.drawable.kotakputih)
                    }, 500)

                    nextquestion()
                    txtnosoal.text = currentNo.toString() + "/3"
                }
            }

            layoutd.setOnClickListener {
                if(trueanswer == "D"){
                    currentscore = 100 + currentscore
                    txtscoregameplay.text = currentscore.toString()
                    layoutd.setBackgroundResource(R.drawable.kotakbenar)

                    Handler().postDelayed({
                        layoutd.setBackgroundResource(R.drawable.kotakputih)
                    }, 500)

                    nextquestion()
                    txtnosoal.text = currentNo.toString() + "/3"
                }
                else{
                    txtscoregameplay.text = currentscore.toString()
                    layoutd.setBackgroundResource(R.drawable.kotaksalah)

                    Handler().postDelayed({
                        layoutd.setBackgroundResource(R.drawable.kotakputih)
                    }, 500)

                    nextquestion()
                    txtnosoal.text = currentNo.toString() + "/3"
                }

            }
        }

        if(learn == 6){

            listData.add(gameplay("Berikut adalah macam - macam kelainan bibir, kecuali" ,
                    "Concrescence",
                    "CHEILITIS",
                    "CELAH BIBIR",
                    "CHEILITIS EKSPOLIATIF",
                    "CONCRESCENCE"))
            listData.add(gameplay("kelainan atopik pada bibir terjadi karena kontak dengan agen tertentu, infeksi mikroorganisme, efek samping pengobatan adalah kelainan yang bernama" ,
                    "CHEILITIS EKSPOLIATIF",
                    "CHEILITIS",
                    "CELAH BIBIR",
                    "CHEILITIS EKSPOLIATIF",
                    "CONCRESCENCE"))
            listData.add(gameplay("Terbentuk fisur berwarna merah terpusat pada sudut bibir, bisa disertai ulkus yang ditutupi oleh lapisan pseudo membran adalah tanda dari kelainan bernama " ,
                    "CHEILITIS",
                    "CHEILITIS",
                    "CELAH BIBIR",
                    "CHEILITIS EKSPOLIATIF",
                    "CONCRESCENCE"))
            listData.add(gameplay("krusta pada batas bibir dengan kulit wajah (Vermillion border ), tampak gambaran keradangan ringan, tidak memiliki keluhan rasa sakit adalah tanda dari kelainan bernama" ,
                    "CHEILITIS EKSPOLIATIF",
                    "CHEILITIS",
                    "CELAH BIBIR",
                    "CHEILITIS EKSPOLIATIF",
                    "CONCRESCENCE"))

            timer.start()
            nextquestion()
            txtnosoal.text =  "1/3"

            layouta.setOnClickListener {
                if(trueanswer == "A"){
                    currentscore = 100 + currentscore
                    txtscoregameplay.text = currentscore.toString()
                    layouta.setBackgroundResource(R.drawable.kotakbenar)

                    Handler().postDelayed({
                        layouta.setBackgroundResource(R.drawable.kotakputih)
                    }, 500)

                    nextquestion()
                    txtnosoal.text = currentNo.toString() + "/3"
                }
                else{
                    txtscoregameplay.text = currentscore.toString()
                    layouta.setBackgroundResource(R.drawable.kotaksalah)

                    Handler().postDelayed({
                        layouta.setBackgroundResource(R.drawable.kotakputih)
                    }, 500)

                    nextquestion()
                    txtnosoal.text = currentNo.toString() + "/3"
                }
            }

            layoutb.setOnClickListener {
                if(trueanswer == "B"){
                    currentscore = 100 + currentscore
                    txtscoregameplay.text = currentscore.toString()
                    layoutb.setBackgroundResource(R.drawable.kotakbenar)

                    Handler().postDelayed({
                        layoutb.setBackgroundResource(R.drawable.kotakputih)
                    }, 500)

                    nextquestion()
                    txtnosoal.text = currentNo.toString() + "/3"
                }
                else{
                    txtscoregameplay.text = currentscore.toString()
                    layoutb.setBackgroundResource(R.drawable.kotaksalah)

                    Handler().postDelayed({
                        layoutb.setBackgroundResource(R.drawable.kotakputih)
                    }, 500)

                    nextquestion()
                    txtnosoal.text = currentNo.toString() + "/3"
                }
            }

            layoutc.setOnClickListener {
                if(trueanswer == "C"){
                    currentscore = 100 + currentscore
                    txtscoregameplay.text = currentscore.toString()
                    layoutc.setBackgroundResource(R.drawable.kotakbenar)

                    Handler().postDelayed({
                        layoutc.setBackgroundResource(R.drawable.kotakputih)
                    }, 500)

                    nextquestion()
                    txtnosoal.text = currentNo.toString() + "/3"
                }
                else{
                    txtscoregameplay.text = currentscore.toString()
                    layoutc.setBackgroundResource(R.drawable.kotaksalah)

                    Handler().postDelayed({
                        layoutc.setBackgroundResource(R.drawable.kotakputih)
                    }, 500)

                    nextquestion()
                    txtnosoal.text = currentNo.toString() + "/3"
                }
            }

            layoutd.setOnClickListener {
                if(trueanswer == "D"){
                    currentscore = 100 + currentscore
                    txtscoregameplay.text = currentscore.toString()
                    layoutd.setBackgroundResource(R.drawable.kotakbenar)

                    Handler().postDelayed({
                        layoutd.setBackgroundResource(R.drawable.kotakputih)
                    }, 500)

                    nextquestion()
                    txtnosoal.text = currentNo.toString() + "/3"
                }
                else{
                    txtscoregameplay.text = currentscore.toString()
                    layoutd.setBackgroundResource(R.drawable.kotaksalah)

                    Handler().postDelayed({
                        layoutd.setBackgroundResource(R.drawable.kotakputih)
                    }, 500)

                    nextquestion()
                    txtnosoal.text = currentNo.toString() + "/3"
                }

            }
        }

        if(learn == 7){

            listData.add(gameplay("indera pengecap yang terdiri dari sejumlah bagian dan memiliki berbagai macam fungsi adalah pengertian dari" ,
                    "lidah",
                    "lidah",
                    "bibir",
                    "gusi",
                    "gigi"))
            listData.add(gameplay("Berikut adalah fungsi dari lidah, kecuali" ,
                    "CHEILITIS EKSPOLIATIF",
                    "membantu kita berkomunikasi",
                    "mengunyah",
                    "menelan makanan",
                    "mencium"))
            listData.add(gameplay("Lidah terdiri dari sekumpulan otot tanpa tulang yang dilapisi oleh jaringan berwarna merah mudah bernama " ,
                    "mukosa",
                    "cheilitis",
                    "mukosa",
                    "dermis",
                    "stratum corneum"))
            listData.add(gameplay("Bagian yang menghubungkan lidah dengan dasar rongga mulut sekaligus berfungsi sebagai penyangga lidah adalah" ,
                    "frenulum",
                    "mukosa",
                    "dermis",
                    "frenulum",
                    "cheilitis"))

            timer.start()
            nextquestion()
            txtnosoal.text =  "1/3"

            layouta.setOnClickListener {
                if(trueanswer == "A"){
                    currentscore = 100 + currentscore
                    txtscoregameplay.text = currentscore.toString()
                    layouta.setBackgroundResource(R.drawable.kotakbenar)

                    Handler().postDelayed({
                        layouta.setBackgroundResource(R.drawable.kotakputih)
                    }, 500)

                    nextquestion()
                    txtnosoal.text = currentNo.toString() + "/3"
                }
                else{
                    txtscoregameplay.text = currentscore.toString()
                    layouta.setBackgroundResource(R.drawable.kotaksalah)

                    Handler().postDelayed({
                        layouta.setBackgroundResource(R.drawable.kotakputih)
                    }, 500)

                    nextquestion()
                    txtnosoal.text = currentNo.toString() + "/3"
                }
            }

            layoutb.setOnClickListener {
                if(trueanswer == "B"){
                    currentscore = 100 + currentscore
                    txtscoregameplay.text = currentscore.toString()
                    layoutb.setBackgroundResource(R.drawable.kotakbenar)

                    Handler().postDelayed({
                        layoutb.setBackgroundResource(R.drawable.kotakputih)
                    }, 500)

                    nextquestion()
                    txtnosoal.text = currentNo.toString() + "/3"
                }
                else{
                    txtscoregameplay.text = currentscore.toString()
                    layoutb.setBackgroundResource(R.drawable.kotaksalah)

                    Handler().postDelayed({
                        layoutb.setBackgroundResource(R.drawable.kotakputih)
                    }, 500)

                    nextquestion()
                    txtnosoal.text = currentNo.toString() + "/3"
                }
            }

            layoutc.setOnClickListener {
                if(trueanswer == "C"){
                    currentscore = 100 + currentscore
                    txtscoregameplay.text = currentscore.toString()
                    layoutc.setBackgroundResource(R.drawable.kotakbenar)

                    Handler().postDelayed({
                        layoutc.setBackgroundResource(R.drawable.kotakputih)
                    }, 500)

                    nextquestion()
                    txtnosoal.text = currentNo.toString() + "/3"
                }
                else{
                    txtscoregameplay.text = currentscore.toString()
                    layoutc.setBackgroundResource(R.drawable.kotaksalah)

                    Handler().postDelayed({
                        layoutc.setBackgroundResource(R.drawable.kotakputih)
                    }, 500)

                    nextquestion()
                    txtnosoal.text = currentNo.toString() + "/3"
                }
            }

            layoutd.setOnClickListener {
                if(trueanswer == "D"){
                    currentscore = 100 + currentscore
                    txtscoregameplay.text = currentscore.toString()
                    layoutd.setBackgroundResource(R.drawable.kotakbenar)

                    Handler().postDelayed({
                        layoutd.setBackgroundResource(R.drawable.kotakputih)
                    }, 500)

                    nextquestion()
                    txtnosoal.text = currentNo.toString() + "/3"
                }
                else{
                    txtscoregameplay.text = currentscore.toString()
                    layoutd.setBackgroundResource(R.drawable.kotaksalah)

                    Handler().postDelayed({
                        layoutd.setBackgroundResource(R.drawable.kotakputih)
                    }, 500)

                    nextquestion()
                    txtnosoal.text = currentNo.toString() + "/3"
                }

            }
        }

        if(learn == 8){

            listData.add(gameplay("Peradangan pada lidah , yang ditandai dengan deskuamasi papila filiformis sehingga menghasilkan daerah kemerahan yang mengkilat adalah kelainan yang bernama" ,
                    "glositis",
                    "lidah geografik",
                    "median rhomboid glositis",
                    "hipertropi papila",
                    "glositis"))
            listData.add(gameplay("Gambaran pola seperti peta pada permukaan dorsum lidah yang tidak diketahui penyebabnya, dan sering terjadi pada wanita " +
                    "adalah kelainan yang bernama" ,
                    "lidah geografik",
                    "lidah geografik",
                    "median rhomboid glositis",
                    "hipertropi papila",
                    "glositis"))
            listData.add(gameplay("Berupa persistensi tonjolan di median posterior lidah akibat kegagalan fungsi tuberkulum impar pada masa embrio " +
                    "adalah kelainan yang bernama" ,
                    "median rhomboid glositis",
                    "lidah geografik",
                    "median rhomboid glositis",
                    "hipertropi papila",
                    "glositis"))
            listData.add(gameplay("merasakan sensasi terbakar, perih, sakit, panas adalah tanda -tanda kelainan yang bernama " ,
                    "glositis",
                    "lidah geografik",
                    "median rhomboid glositis",
                    "hipertropi papila",
                    "glositis"))
            timer.start()
            nextquestion()
            txtnosoal.text =  "1/3"

            layouta.setOnClickListener {
                if(trueanswer == "A"){
                    currentscore = 100 + currentscore
                    txtscoregameplay.text = currentscore.toString()
                    layouta.setBackgroundResource(R.drawable.kotakbenar)

                    Handler().postDelayed({
                        layouta.setBackgroundResource(R.drawable.kotakputih)
                    }, 500)

                    nextquestion()
                    txtnosoal.text = currentNo.toString() + "/3"
                }
                else{
                    txtscoregameplay.text = currentscore.toString()
                    layouta.setBackgroundResource(R.drawable.kotaksalah)

                    Handler().postDelayed({
                        layouta.setBackgroundResource(R.drawable.kotakputih)
                    }, 500)

                    nextquestion()
                    txtnosoal.text = currentNo.toString() + "/3"
                }
            }

            layoutb.setOnClickListener {
                if(trueanswer == "B"){
                    currentscore = 100 + currentscore
                    txtscoregameplay.text = currentscore.toString()
                    layoutb.setBackgroundResource(R.drawable.kotakbenar)

                    Handler().postDelayed({
                        layoutb.setBackgroundResource(R.drawable.kotakputih)
                    }, 500)

                    nextquestion()
                    txtnosoal.text = currentNo.toString() + "/3"
                }
                else{
                    txtscoregameplay.text = currentscore.toString()
                    layoutb.setBackgroundResource(R.drawable.kotaksalah)

                    Handler().postDelayed({
                        layoutb.setBackgroundResource(R.drawable.kotakputih)
                    }, 500)

                    nextquestion()
                    txtnosoal.text = currentNo.toString() + "/3"
                }
            }

            layoutc.setOnClickListener {
                if(trueanswer == "C"){
                    currentscore = 100 + currentscore
                    txtscoregameplay.text = currentscore.toString()
                    layoutc.setBackgroundResource(R.drawable.kotakbenar)

                    Handler().postDelayed({
                        layoutc.setBackgroundResource(R.drawable.kotakputih)
                    }, 500)

                    nextquestion()
                    txtnosoal.text = currentNo.toString() + "/3"
                }
                else{
                    txtscoregameplay.text = currentscore.toString()
                    layoutc.setBackgroundResource(R.drawable.kotaksalah)

                    Handler().postDelayed({
                        layoutc.setBackgroundResource(R.drawable.kotakputih)
                    }, 500)

                    nextquestion()
                    txtnosoal.text = currentNo.toString() + "/3"
                }
            }

            layoutd.setOnClickListener {
                if(trueanswer == "D"){
                    currentscore = 100 + currentscore
                    txtscoregameplay.text = currentscore.toString()
                    layoutd.setBackgroundResource(R.drawable.kotakbenar)

                    Handler().postDelayed({
                        layoutd.setBackgroundResource(R.drawable.kotakputih)
                    }, 500)

                    nextquestion()
                    txtnosoal.text = currentNo.toString() + "/3"
                }
                else{
                    txtscoregameplay.text = currentscore.toString()
                    layoutd.setBackgroundResource(R.drawable.kotaksalah)

                    Handler().postDelayed({
                        layoutd.setBackgroundResource(R.drawable.kotakputih)
                    }, 500)

                    nextquestion()
                    txtnosoal.text = currentNo.toString() + "/3"
                }

            }
        }

        requireActivity()
                .onBackPressedDispatcher
                .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        timer.cancel()
                        val intent = Intent (requireActivity(), HomeActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                    }
                })
    }

    fun nextquestion(){
        val numberquiz = listData.size - 1
        val randomnumber = (0..numberquiz).random()
        txtsoal.text = listData[randomnumber].soal
        ranktxta.text = listData[randomnumber].pila
        ranktxtb.text = listData[randomnumber].pilb
        ranktxtc.text = listData[randomnumber].pilc
        ranktxtd.text = listData[randomnumber].pild

        if(listData[randomnumber].jawaban == listData[randomnumber].pila){
            trueanswer = "A"

        }
        else if(listData[randomnumber].jawaban == listData[randomnumber].pilb){
            trueanswer = "B"
        }
        else if(listData[randomnumber].jawaban == listData[randomnumber].pilc){
            trueanswer = "C"
        }
        else if(listData[randomnumber].jawaban == listData[randomnumber].pild){
            trueanswer = "D"
        }

        currentNo ++
        listData.removeAt(randomnumber)

        if(currentNo > 3){
            if(levellist.contains("0")){
                listLevel.add("0")
            }
            if(levellist.contains("1")){
                listLevel.add("1")
            }
            if(levellist.contains("2")){
                listLevel.add("2")
            }
            if(levellist.contains("3")){
                listLevel.add("3")
            }
            if(levellist.contains("4")){
                listLevel.add("4")
            }
            if(levellist.contains("5")){
                listLevel.add("5")
            }
            if(levellist.contains("6")){
                listLevel.add("6")
            }
            if(levellist.contains("7")){
                listLevel.add("7")
            }
            if(levellist.contains("8")){
                listLevel.add("8")
            }
            if(levellist.contains("9")){
                listLevel.add("9")
            }

            if(currentscore == 300){
                if(!levellist.contains(learn.toString())){
                    listLevel.add(learn.toString())
                }

                if(!achiev.contains("2")){
                    listAchiev.add(2)
                    database.collection("ms_user").document(mAuth.uid.toString()).update("achievementList",listAchiev )
                    Toast.makeText( requireContext(),"You Have Unlocked New Achievement", Toast.LENGTH_SHORT).show()
                }


            }

            if(learn == 1){
                if(lvl1score != 300){
                    database.collection("ms_user").document(mAuth.uid.toString()).update("score",currentscore +  totalscore,
                            "money",currentscore +  totalcoin,"updatescore",currentscore, "level",listLevel,"level1score",currentscore)
                }
                else{
                    database.collection("ms_user").document(mAuth.uid.toString()).update("updatescore",currentscore, "level",listLevel)
                }
            }


            if(learn == 2){
                if(lvl2score != 300){
                    database.collection("ms_user").document(mAuth.uid.toString()).update("score",currentscore +  totalscore,
                            "money",currentscore +  totalcoin,"updatescore",currentscore, "level",listLevel,"level2score",currentscore)
                }
                else{
                    database.collection("ms_user").document(mAuth.uid.toString()).update("updatescore",currentscore, "level",listLevel)
                }
            }

            if(learn == 3){
                if(lvl3score != 300){
                    database.collection("ms_user").document(mAuth.uid.toString()).update("score",currentscore +  totalscore,
                            "money",currentscore +  totalcoin,"updatescore",currentscore, "level",listLevel,"level3score",currentscore)
                }
                else{
                    database.collection("ms_user").document(mAuth.uid.toString()).update("updatescore",currentscore, "level",listLevel)
                }
            }

            if(learn == 4){
                if(lvl4score != 300){
                    database.collection("ms_user").document(mAuth.uid.toString()).update("score",currentscore +  totalscore,
                            "money",currentscore +  totalcoin,"updatescore",currentscore, "level",listLevel,"level4score",currentscore)
                }
                else{
                    database.collection("ms_user").document(mAuth.uid.toString()).update("updatescore",currentscore, "level",listLevel)
                }
            }

            if(learn == 5){
                if(lvl5score != 300){
                    database.collection("ms_user").document(mAuth.uid.toString()).update("score",currentscore +  totalscore,
                            "money",currentscore +  totalcoin,"updatescore",currentscore, "level",listLevel,"level5score",currentscore)
                }
                else{
                    database.collection("ms_user").document(mAuth.uid.toString()).update("updatescore",currentscore, "level",listLevel)
                }
            }

            if(learn == 6){
                if(lvl6score != 300){
                    database.collection("ms_user").document(mAuth.uid.toString()).update("score",currentscore +  totalscore,
                            "money",currentscore +  totalcoin,"updatescore",currentscore, "level",listLevel,"level6score",currentscore)
                }
                else{
                    database.collection("ms_user").document(mAuth.uid.toString()).update("updatescore",currentscore, "level",listLevel)
                }
            }

            if(learn == 7){
                if(lvl7score != 300){
                    database.collection("ms_user").document(mAuth.uid.toString()).update("score",currentscore +  totalscore,
                            "money",currentscore +  totalcoin,"updatescore",currentscore, "level",listLevel,"level7score",currentscore)
                }
                else{
                    database.collection("ms_user").document(mAuth.uid.toString()).update("updatescore",currentscore, "level",listLevel)
                }
            }
            if(learn == 8){
                if(lvl8score != 300){
                    database.collection("ms_user").document(mAuth.uid.toString()).update("score",currentscore +  totalscore,
                            "money",currentscore +  totalcoin,"updatescore",currentscore, "level",listLevel,"level8score",currentscore)
                }
                else{
                    database.collection("ms_user").document(mAuth.uid.toString()).update("updatescore",currentscore, "level",listLevel)
                }
            }

            if(learn == 9){
                if(lvl9score != 300){
                    database.collection("ms_user").document(mAuth.uid.toString()).update("score",currentscore +  totalscore,
                            "money",currentscore +  totalcoin,"updatescore",currentscore, "level",listLevel,"level9score",currentscore)
                }
                else{
                    database.collection("ms_user").document(mAuth.uid.toString()).update("updatescore",currentscore, "level",listLevel)
                }
            }

            if(learn == 10){
                if(lvl10score != 300){
                    database.collection("ms_user").document(mAuth.uid.toString()).update("score",currentscore +  totalscore,
                            "money",currentscore +  totalcoin,"updatescore",currentscore, "level",listLevel,"level10score",currentscore)
                }
                else{
                    database.collection("ms_user").document(mAuth.uid.toString()).update("updatescore",currentscore, "level",listLevel)
                }
            }






            layouta.isClickable = false
            layoutb.isClickable = false
            layoutc.isClickable = false
            layoutd.isClickable = false

            timer.cancel()

            Handler().postDelayed({
                navController.navigate(R.id.action_gameplayFragment_to_endgameFragment)
            }, 600)

        }
    }

}