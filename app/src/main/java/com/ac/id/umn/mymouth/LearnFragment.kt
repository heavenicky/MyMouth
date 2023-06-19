package com.ac.id.umn.mymouth

import android.R.attr.name
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_learn.*


class LearnFragment : Fragment() {

    private lateinit var navController: NavController

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_learn, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var learn = requireActivity().intent.getIntExtra("Status", 0)
        navController = Navigation.findNavController(view)
        if(learn == 1){
            learnpct.setImageResource(R.drawable.mulut)
/*            val sourceString = "<b>" + "mulut" + "</b> " + "organ"
            txtlearn.setText(Html.fromHtml(sourceString));*/
            txtlearn.text =  "Mulut merupakan salah satu organ yang punya beberapa fungsi penting bagi tubuh. " +
                    "Rongga yang dimulai dari bibir dan berakhir di tenggorokan ini  memungkinkan kamu untuk bernapas, berbicara, dan mencerna makanan." +
                    "Dalam sistem pencernaan manusia, mulut adalah organ pertama dari saluran pencernaan. Didalam mulut, terdapat beberapa bagian seperti bibir, gigi, gusi, dan lidah.\n \nBibir berfungsi sebagai pembukaan untuk asupan makanan dan dalam artikulasi suara dan bicara.\n\n " +
                    "Gusi adalah jaringan yang menahan gigi kamu pada tempatnya. Gusi memiliki warna merah muda yang menandakan adanya pembuluh darah di baliknya.\n \n " +
                    "Lidah merupakan otot kuat yang memiliki taste buds. Organ ini membantu menggerakkan makanan di sekitar mulut dan membantu kamu berbicara.\n \n" +
                    "Mulut memiliki 32 gigi yang bisa menghancurkan dan merobek makanan untuk ditelan dan dicerna."
        }
        if(learn == 2){
            learnpct.setImageResource(R.drawable.kariesgigi)
            txtlearn.text = "Karies gigi atau gigi berlubang," +
                    "merupakan kerusakan gigi akibat " +
                    "infeksi bakteri yang merusak lapisan " +
                    "gigi sehingga merusak struktur gigi. Bakteri " +
                    "pada mulut mengolah gula sehingga " +
                    "menghasilkan asam. Asam yang " +
                    "diproduksi selama metabolisme dalam " +
                    "mulut ini dapat merusak gigi. Gigi " +
                    "berlubang dapat menyebabkan nyeri " +
                    "pada gigi jika sampai terlalu dalam " +
                    "kerusakannya karena telah sampai " +
                    "merusak saraf gigi. Pada umumnya penyakit gigi dan mulut disebabkan oleh " +
                    "kurangnya menjaga kebersihan mulut. \n \n Oleh karena itu, upaya " +
                    "pencegahan yang dapat kamu lakukan adalah dengan memerhatikan " +
                    "kebersihan gigi. Menyikat gigi minimal 2 kali sehari, membersihkan " +
                    "gigi dengan menggunakan benang gigi, obat kumur atau berkumur " +
                    "dengan larutan garam dan air hangat dapat membantu mengurangi " +
                    "plak pada gigi, serta pemeriksaan gigi secara teratur dapat mengurangi " +
                    "perkembangan bakteri yang menyebabkan terjadinya penyakit pada " +
                    "mulut dan gigi. \n \n Selain itu kamu juga harus mengurangi makanan - makanan manis seperti permen, minuman bersoda, atau makanan " +
                    "manis lainnya agar jumlah plak yang menempel pada gigi berkurang. " +
                    "Kamu dapat berkumur dengan air setelah banyak makan makanan " +
                    "manis. Perbanyak minum air putih juga dapat mengurangi plak yang " +
                    "menempel pada gigi. "
        }
        if(learn == 3){
            learnpct.setImageResource(R.drawable.gigilearn)
            txtlearn.text = "Gigi adalah jaringan tubuh yang sangat keras dibanding yang lainnya. " +
                    "Strukturnya berlapis-lapis mulai dari email yang keras, dentin (tulang gigi) di" +
                    "dalamnya, pulpa yang berisi pembuluh darah, pembuluh saraf, dan bagian lain yang " +
                    "memperkokoh gigi. Namun demikian, gigi merupakan jaringan tubuh yang mudah " +
                    "sekali mengalami kerusakan. \n \n " +
                    "Manusia mempunyai 2 macam gigi yaitu gigi susu dan gigi dewasa. Gigi " +
                    "susu merupakan gigi yang tumbuh pada anak usia 6 bulan hingga 8 tahun. Jumlah " +
                    "gigi ini pada anak yakni 20 buah dengan rincian 8 buah gigi seri, 4 buah gigi taring, " +
                    "dan 8 buah gigi geraham. Gigi dewasa atau gigi " +
                    "tetap merupakan gigi orang dewasa yang berjumlah 32 buah. Rinciannya 8 " +
                    "buah gigi seri, 4 buah gigi taring, 8 buah gigi geraham depan, dan 12 buah gigi " +
                    "geraham belakang"
        }
        if(learn == 4){
            learnpct.setImageResource(R.drawable.anomaligigi)
            txtlearn.text = "Anomali gigi adalah suatu penyimpangan dari bentuk normal akibat gangguan pada stadium " +
                    "pertumbuhan dan perkembangan gigi. Jenis anomali gigi dibedakan menjadi 4 macam tergantung anomali nya. anomali tersebut adalah : \n \n" +
                    " 1. Anomali berdasarkan jumlah \n " +
                    "anomali ini terjadi dikarenakan adanya kelebihan atau kekurangan gigi. terdapat 2 kondisi dimana keadaan benih gigi tidak ada yaitu : \n " +
                    "     a. Anodontia Partialis ( Agenesis Soliter ) : tidak terbentuk satu atau beberapa gigi \n" +
                    "     b. Anodontia Totalis ( Agenesis Absolut ) : tidak adanya seluruh benih gigi \n \n" +
                    "2. Anomali berdasarkan bentuk \n " +
                    "     a. Dilaserasi : adalah akar dan mahkota gigi yang sangat bengkok / distorsi, sering membentuk" +
                    "sudut 45 derajat sampai lebih dari 90 derajat. \n" +

                    "     b. Concrescence : Keadaan ini adalah fusion atau tumbuh jadi satu pada akar gigi melalui sementum saja " +
                    "     c. Hipersementosis : Pembentukan jaringan sementum yang berlebihan sekitar akar gigi setelah gigi erupsi, \n" +
                    "dapat disebabkan oleh trauma,gangguan metabolisme, atau infeksi periapikal. \n" +
                    "     d. Ankylosis : Gigi menjadi satu dengan tulang \n" +
                    "     e. Flexion : Akar gigi yang bengkok kurang dari 90 derajat atau mutar \n \n" +

                    "3. Anomali berdasarkan ukuran \n " +
                    "     a. Mikrodonsia : gigi dengan ukuran lebih kecil dari ukuran normal \n" +
                    "     b. Makrodonsia : gigi dengan ukuran lebih besar dari ukuran normal \n \n" +
                    "4. Anomali berdasarkan posisi \n  " +
                    "adalah penyimpangan posisi gigi dari posisi normal diakibatkan oleh gangguan dalam \n" +
                    "stadium pertumbuhan dan perkembangan."
        }
        if(learn == 5){
            learnpct.setImageResource(R.drawable.bibir)
            txtlearn.text = "Lapisan terluar dari kulit adalah epidermis dan lapisan ini " +
                    "mempunyai pelindung yang disebut dengan stratum corneum. Di bawah " +
                    "lapisan epidermis ini terdapat dermis. Seperti bagian lain pada kulit, bibir\n " +
                    "memiliki ketiga lapisan tersebut, perbedaannya adalah lapisan stratum\n " +
                    "corneum pada bibir lebih tipis dari lapisan kulit lain pada tubuh. Bibir " +
                    "juga tidak mempunyai kelenjar minyak yang menjaga kulit tetap lembab " +
                    "dan satu-satunya sumber pelembab bagi bibir adalah air liur. Bibir mempunyai sedikit keratin dan kulit bibir relatif lebih tipis " +
                    "dibandingkan lapisan kulit pada umumnya. Bibir juga tidak mempunyai " +
                    "pigmen melanin sehingga pembuluh darah kapiler dapat terlihat dan " +
                    "menyebabkan bibir berwarna merah. Bibir berfungsi sebagai tempat artikulasi, membantu menahan udara dari paru-paru sehingga dihasilkan bunyi-bunyi tertentu. Karena kita punya bibir, kita bisa memiliki huruf b, p, dan m."
        }
        if(learn == 6){
            learnpct.setImageResource(R.drawable.anomalibibir)
            txtlearn.text = "Macam- macam Kelainan pada Bibir : \n \n " +
                    "a. CHEILITIS \n " +
                    "Merupakan infeksi yang mengenai sudut bibir. penyebab dari kelainan ini adalah deffisiensi nutrisi tertentu ,seperti Fe , vitamin B atau asam " +
                    "folat atau dapat disebabkan karena dimensi vertikal gigi tiruan yang tidak tepat.\n" +
                    "Tanda - tanda nya adalah Terbentuk fisur berwarna merah terpusat pada sudut bibir, bisa disertai ulkus yang \n" +
                    "ditutupi oleh lapisan pseudo membran. \n " +
                    "cara untuk mengobati nya adalah dengan menghilangkan etiologi dengan pemberian obat anti jamur atau pemberian vitamin \n \n " +
                    "b. CHEILITIS EKSPOLIATIF \n" +
                    "Adalah kelainan atopik pada bibir terjadi karena kontak dengan agen tertentu, infeksi " +
                    "mikroorganisme, efek samping pengobatan. \n" +
                    "Tanda - tanda nya adalah Berupa krusta pada batas bibir dengan kulit wajah (Vermillion border ), tampak " +
                    "gambaran keradangan ringan, tidak memiliki keluhan rasa sakit. \n" +
                    "Cara untuk mengobati nya adalah dengan menghilangkan etiologi dengan pemberian vitamin ,lip oitment (borax gliserin / " +
                    "vaselin. \n \n" +
                    "c. CELAH BIBIR \n" +
                    "Merupakan cacat bawaan berupa celah pada bibir ( labiochisis) dapat terjadi bilateral " +
                    "atau unilateral, terjadi kegagalan penyatuan antara prosesus lasalis dan prosesus " +
                    "maksilaris pada embrio trimester pertama"
        }

        if(learn == 7){
            learnpct.setImageResource(R.drawable.lidah)
            txtlearn.text = "Lidah merupakan indera pengecap yang terdiri dari sejumlah bagian dan " +
                    "memiliki berbagai macam fungsi. Selain berfungsi sebagai pengecap, lidah juga memiliki " +
                    "beberapa fungsi utama, antara lain membantu kita berkomunikasi, mengunyah, dan menelan makanan.\n" +
                    "Untuk menjalankan fungsinya, lidah dibantu oleh sejumlah otot dan saraf yang langsung terhubung ke otak. " +
                    "Keberadaan otot-otot inilah yang membuat lidah bisa bergerak bebas ke segala arah di dalam rongga mulut. \n " +
                    "Lidah terdiri dari sekumpulan otot tanpa tulang yang dilapisi oleh jaringan berwarna merah mudah bernama mukosa. " +
                    "Satu-satunya tulang yang berhubungan langsung dengan lidah adalah tulang hyoid. Tulang ini terletak di antara leher dan dagu bagian dalam. " +
                    "Lidah juga memiliki bagian lain yang disebut dengan frenulum. Bagian ini menghubungkan lidah dengan dasar rongga mulut sekaligus berfungsi " +
                    "sebagai penyangga lidah."
        }

        if(learn == 8){
            learnpct.setImageResource(R.drawable.anomalilidah)
            txtlearn.text = "Terdapat beberapa macam kelainan lidah, antara lain : \n" +
                    "a. GLOSITIS \n" +
                    "Peradangan pada lidah , yang ditandai dengan deskuamasi papila filiformis sehingga " +
                    "menghasilkan daerah kemerahan yang mengkilat. Tanda - tanda kelainan ini adalah merasakan sensasi terbakar, perih, sakit, panas. \n " +
                    "untuk penyembuhannya, tergantung penyebab, untuk yang disebabkan Crohn desease harus dirujuk pada " +
                    "dokter gigi spesialis penyakit mulut \n" +
                    "b. LIDAH GEOGRAFIK \n" +
                    "Gambaran pola seperti peta pada permukaan dorsum lidah yang tidak diketahui " +
                    "penyebabnya, sering terjadi pada wanita. \n" +
                    "Tanda- tandanya adalah dorsum lidah terlihat bercak merah tidak teratur, dikelilingi daerah memutih yang " +
                    "sedikit meninggi, terlihat seperti peta , polanya berubah dari waktu kewaktu \n " +
                    "c. MEDIAN RHOMBOID GLOSITIS \n " +
                    "Berupa persistensi tonjolan di median posterior lidah akibat kegagalan fungsi " +
                    "tuberkulum impar pada masa embrio. \n " +
                    "tanda - tanda dari kelainan ini adalah tonjolan berbentuk belah ketupat, pada permukaan dorsum lidah di median posterior " +
                    "berwarna kemerahan karena tidak ada papila atau berwarna keputihan bila terinfeksi. \n " +
                    "cara untuk mengatasi nya adalah Membersihkan lidah dengan tongue scraper, bila ada infeksi Candida albicans" +
                    "diberikan Nystatin tetes mulut secara topikal pada lidah sehari 3 kali"
        }

        btnNext.setOnClickListener {
            navController.navigate(R.id.action_learnFragment_to_gameplayFragment)
        }

    }

}