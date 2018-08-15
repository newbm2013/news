package artemshumidub.ru.sebbianews.ui.activity.base;

/**
 * Родительский интерфейс для всех контрактов
 *
 * @author Artem humidub
 */

public interface IViewContract {

    /**
     * Нет интернета
     */
    void showInternetError();

    /**
     * Ошибка сервера
     */
    void showServerError();

    /**
     * Нет контента
     */
    void showEmptyState();

    /**
    * Старт прогресса
    */
    void startProgress();

    /**
     * Стоп прогресса
     */
    void stopProgress();

}
