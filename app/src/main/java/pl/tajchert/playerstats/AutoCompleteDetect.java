package pl.tajchert.playerstats;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.AutoCompleteTextView;

import de.greenrobot.event.EventBus;

/**
 * Created by Michal Tajchert on 2015-05-11.
 */
public class AutoCompleteDetect extends AutoCompleteTextView {

    public AutoCompleteDetect(Context context) {
        super(context);
    }

    public AutoCompleteDetect(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoCompleteDetect(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            // Do your thing.
            EventBus.getDefault().post(new EventKeyboardClosed());
            return true;  // So it is not propagated.
        }
        return super.dispatchKeyEvent(event);
    }

    public class EventKeyboardClosed {

    }
}
