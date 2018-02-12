package com.londonappbrewery.quizzler;

/**
 * A more appropriate class for Q&A.
 */

class Question {

    final private int question;
    final private boolean answer;
    //----------------------------------------------------------------------------------------------
    /**
     * Question has mandatory args.
     * @param question The quiz question.
     * @param answer The answer to the quiz question.
     */
    Question(int question, boolean answer) {
        this.question = question;
        this.answer = answer;
    }
    //----------------------------------------------------------------------------------------------
    /**
     * Question getter.
     * @return Returns the question ID.
     */
    int pose() {
        return question;
    }
    //----------------------------------------------------------------------------------------------

    /**
     * Answer getter.
     * @return Returns the answer.
     */
    boolean answer() {
        return answer;
    }
    //----------------------------------------------------------------------------------------------
}
