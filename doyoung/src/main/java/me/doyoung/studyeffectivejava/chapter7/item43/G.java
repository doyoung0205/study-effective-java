package me.doyoung.studyeffectivejava.chapter7.item43;

public interface G extends G1, G2 {
    @Override
    <E extends Exception> String m() throws E;
}
