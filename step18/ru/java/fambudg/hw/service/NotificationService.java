package ru.java.fambudg.hw.service;

public class NotificationService {
    
    public void notifyReportStarted(String userId, String reportId) {
        System.out.println(String.format(
            "Пользователь %s: начато формирование отчета '%s'",
            userId, reportId
        ));
    }
    
    public void notifyReportReady(String userId, String reportId, String result) {
        System.out.println(String.format(
            "Пользователь %s: отчет '%s' готов!\nРезультат:\n%s",
            userId, reportId, result
        ));
    }
    
    public void notifyError(String userId, String reportId, String error) {
        System.out.println(String.format(
            "Пользователь %s: ошибка при формировании отчета '%s': %s",
            userId, reportId, error
        ));
    }
}
