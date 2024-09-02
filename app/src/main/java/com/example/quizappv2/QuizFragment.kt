package com.example.quizappv2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.quizappv2.databinding.FragmentQuizBinding
import com.google.android.material.navigation.NavigationView

class QuizFragment : Fragment(), NavigationView.OnNavigationItemSelectedListener {

    private var _binding: FragmentQuizBinding? = null
    private val binding get() = _binding!!

    private var currentQuestionIndex = 0
    private var score = 0

    private val questions = arrayOf(
        "Τι είναι το βιοντίζελ;",
        "Ποια είναι η κύρια χρήση του βιοντίζελ;",
        "Ποια χώρα είναι από τις μεγαλύτερες παραγωγούς βιοντίζελ από τηγανέλαιο;",
        "Ποιο είναι το κύριο πλεονέκτημα του βιοντίζελ σε σχέση με το συμβατικό ντίζελ;",
        "Ποια από τα παρακάτω δεν αποτελεί πρόκληση για την παραγωγή βιοντίζελ από τηγανέλαιο;",
        "Πώς μπορεί να αυξηθεί η αποτελεσματικότητα της διαδικασίας ανακύκλωσης τηγανελαίου για την παραγωγή βιοντίζελ;",
        "Ποια είναι η πρώτη φάση στη διαδικασία μετατροπής του τηγανέλαιου σε βιοντίζελ;",
        "Ποιο από τα παρακάτω είναι ένα κύριο συστατικό που προστίθεται στο τηγανέλαιο για την παραγωγή βιοντίζελ;",
        "Ποιο από τα παρακάτω είναι ένα πιθανό πρόβλημα αν το τηγανέλαιο απορρίπτεται ακατάλληλα;",
        "Ποια είναι η περιβαλλοντική επίπτωση της καύσης βιοντίζελ σε σχέση με το πετρέλαιο;",
        "Ποιος είναι ο κύριος λόγος για τον οποίο τα εστιατόρια και οι καφετέριες ανακυκλώνουν το τηγανέλαιο τους;",
        "Ποιος είναι ο κύριος λόγος για τον οποίο πρέπει να γίνεται προσεκτικός καθαρισμός του τηγανέλαιου πριν τη μετατροπή του σε βιοντίζελ;",
        "Ποια είναι η διάρκεια ζωής του βιοντίζελ σε σχέση με το παραδοσιακό πετρέλαιο κίνησης;",
        "Ποια είναι η περιεκτικότητα του βιοντίζελ σε ενέργεια σε σχέση με το πετρέλαιο κίνησης;",
        "Ποια είναι η βασική μέθοδος συλλογής τηγανέλαιου που αποθηκεύτηκε από τα νοικοκυριά και τις επιχειρήσεις;",
        "Ποια είναι η κύρια πρόκληση που αντιμετωπίζουν οι παραγωγοί βιοντίζελ από τηγανέλαιο;"
    )

    private val options = arrayOf(
        arrayOf(
            "1. Είναι μία άλλη ονομασία του πετρελαίου.",
            "2. Είναι ένα είδος καυσίμου που παράγεται από φυτικά έλαια και ζωικά λίπη.",
            "3. Είναι ένα είδος άνθρακα που χρησιμοποιείται για θέρμανση.",
            "4. Είναι ένα είδος καυσίμου που παράγεται από νερό και αέρα."
        ),
        arrayOf(
            "1. Στην παραγωγή ηλεκτρικής ενέργειας.",
            "2. Ως καύσιμο για αεροσκάφη.",
            "3. Ως καύσιμο για πετρελαιοκίνητα οχήματα.",
            "4. Για τη θέρμανση."
        ),
        arrayOf(
            "1. Κίνα.",
            "2. Βραζιλία.",
            "3. Ηνωμένες Πολιτείες.",
            "4. Γερμανία."
        ),
        arrayOf(
            "1. Είναι πιο οικονομικό.",
            "2. Έχει υψηλότερη ενεργειακή απόδοση.",
            "3. Είναι βιοδιασπώμενο και λιγότερο τοξικό.",
            "4. μεγαλύτερη διάρκεια ζωής στους κινητήρες."
        ),
        arrayOf(
            "1. Η συλλογή και αποθήκευση του χρησιμοποιημένου τηγανέλαιου.",
            "2. Η σταθερή ποιότητα του τηγανελαίου.",
            "3. Η εύκολη και ανέξοδη μετατροπή του σε βιοντίζελ.",
            "4. Η απομάκρυνση των ακαθαρσιών από το τηγανέλαιο πριν την επεξεργασία."
        ),
        arrayOf(
            "1. Να βελτιωθεί η διαδικασία συλλογής τηγανελαίου.",
            "2. Να αποθηκεύεται σε κατάλληλα δοχεία.",
            "3. Να αναλαμβάνουν την διαδικασία μόνο εξειδικευμένοι χρήστες.",
            "4. Να αποθηκεύεται το λάδι που έχει χρησιμοποιηθεί μόνο στο τηγάνισμα πατάτας."
        ),
        arrayOf(
            "1. Διύλιση.",
            "2. Μετατροπή.",
            "3. Καθαρισμός.",
            "4. Μείξη."
        ),
        arrayOf(
            "1. Υδρογόνο.",
            "2. Μεθανόλη.",
            "3. Αιθανόλη.",
            "4. Νερό."
        ),
        arrayOf(
            "1. Αύξηση της θερμοκρασίας του νερού.",
            "2. Μόλυνση του εδάφους.",
            "3. Αλλαγή του κλίματος.",
            "4. Διάβρωση του εδάφους."
        ),
        arrayOf(
            "1. Αυξημένες εκπομπές διοξειδίου του θείου.",
            "2. Μειωμένες εκπομπές διοξειδίου του άνθρακα.",
            "3. Αυξημένη κατανάλωση νερού.",
            "4. Αυξημένες εκπομπές μονοξειδίου του άνθρακα."
        ),
        arrayOf(
            "1. Για να μειώσουν τα έξοδα απόρριψης αποβλήτων.",
            "2. Για να αυξήσουν τα κέρδη τους.",
            "3. Για να βελτιώσουν την ποιότητα των φαγητών τους.",
            "4. Για να μειώσουν την κατανάλωση νερού."
        ),
        arrayOf(
            "1. Για να αυξηθεί η θερμογόνος δύναμη του βιοντίζελ.",
            "2. Για να μειωθούν οι εκπομπές διοξειδίου του άνθρακα.",
            "3. Για να αποφευχθεί η ζημιά στον εξοπλισμό παραγωγής.",
            "4. Για να βελτιωθεί η γεύση του τελικού προϊόντος."
        ),
        arrayOf(
            "1. Περίπου η ίδια.",
            "2. Μικρότερη.",
            "3. Μεγαλύτερη.",
            "4. Διπλάσια."
        ),
        arrayOf(
            "1. Περίπου 10% λιγότερη ενέργεια.",
            "2. Ισοδύναμη ενέργεια.",
            "3. Περίπου 10% περισσότερη ενέργεια.",
            "4. Διπλάσια ενέργεια."
        ),
        arrayOf(
            "1. Δωρεάν διανομή δοχείων συλλογής.",
            "2. Συλλογή από ειδικούς κάδους ανακύκλωσης.",
            "3. Απόρριψη στο αποχετευτικό σύστημα.",
            "4. Χρήση για λιπάσματα."
        ),
        arrayOf(
            "1. Η εύρεση φθηνών πρώτων υλών.",
            "2. Η διαθεσιμότητα επαρκών ποσοτήτων τηγανέλαιου.",
            "3. Η ρύπανση του αέρα από την παραγωγή.",
            "4. Η έλλειψη τεχνολογίας."
        )
    )

    private val correctAnswers = arrayOf(1, 2, 2, 2, 2, 0, 1, 1, 1, 0, 2, 1, 0, 1, 1, 1)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        displayQuestion()

        binding.option1Button.setOnClickListener { checkAnswer(0) }
        binding.option2Button.setOnClickListener { checkAnswer(1) }
        binding.option3Button.setOnClickListener { checkAnswer(2) }
        binding.option4Button.setOnClickListener { checkAnswer(3) }
        binding.restartButton.setOnClickListener { restartQuiz() }
    }

    private fun displayQuestion() {
        if (currentQuestionIndex < questions.size) {
            binding.questionText.text = questions[currentQuestionIndex]
            binding.option1Button.text = options[currentQuestionIndex][0]
            binding.option2Button.text = options[currentQuestionIndex][1]
            binding.option3Button.text = options[currentQuestionIndex][2]
            binding.option4Button.text = options[currentQuestionIndex][3]
        }
    }

    private fun checkAnswer(selectedAnswerIndex: Int) {
        if (selectedAnswerIndex == correctAnswers[currentQuestionIndex]) {
            score++
        }

        currentQuestionIndex++
        if (currentQuestionIndex < questions.size) {
            displayQuestion()
        } else {
            showFinalScore()
        }
    }

    private fun showFinalScore() {
        binding.questionText.text = "Your score is $score out of ${questions.size}."
        binding.option1Button.isEnabled = false
        binding.option2Button.isEnabled = false
        binding.option3Button.isEnabled = false
        binding.option4Button.isEnabled = false
    }

    private fun restartQuiz() {
        currentQuestionIndex = 0
        score = 0
        binding.option1Button.isEnabled = true
        binding.option2Button.isEnabled = true
        binding.option3Button.isEnabled = true
        binding.option4Button.isEnabled = true
        displayQuestion()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_quiz -> {
                Toast.makeText(context, "Quiz", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_features -> {
                Toast.makeText(context, "Χαρακτηριστικά", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_process -> {
                Toast.makeText(context, "Διαδικασία", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_help -> {
                Toast.makeText(context, "Βοήθεια", Toast.LENGTH_SHORT).show()
            }
        }
        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
