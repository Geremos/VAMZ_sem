package com.example.vamz_sem.filmy

/**
 * Rozhranie poskytujúce počúvače udalostí pre dolný panel.
 */
interface BottomPanelClickListener {
    /**
     * Metóda volaná pri pridávaní filmu do histórie.
     */
    fun addToHistory()
    /**
     * Metóda volaná pri pridávaní filmu do zoznamu "Môj zoznam".
     */
    fun addToMyList()
}