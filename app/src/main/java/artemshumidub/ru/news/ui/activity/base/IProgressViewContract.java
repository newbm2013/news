package artemshumidub.ru.news.ui.activity.base;

/**
 * Родительский интерфейс для контрактов с прогрессом
 *
 * @author Artem humidub
 */

public interface IProgressViewContract {

    /**
    * Старт прогресса
    */
    void startProgress();

    /**
     * Стоп прогресса
     */
    void stopProgress();

}
