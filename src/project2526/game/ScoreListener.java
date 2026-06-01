package project2526.game;

public
    interface ScoreListener {

    void fireOnStartEvent(StartEvent e);
    void fireOnPlusOneEvent(PlusOneEvent e);
    void fireOnResetEvent(ResetEvent e);
}
