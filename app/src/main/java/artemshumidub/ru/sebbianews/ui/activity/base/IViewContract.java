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
     * Остальные ошибки
     */
    void showUnknownError();

    /**
     * Нет контента
     */
    void showEmptyContentMessage();

    /**
     * скрыть контент и все empty states (нет интернета, ошибка ссервера, нет контента, прогресс)
     */
    void clearScreen();

}
