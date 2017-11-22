package lopez.irving.favorites.mvvm.view.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.DimenRes;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import lopez.irving.favorites.R;

public class RoundedCardView extends RelativeLayout {
    private float radius;

    public RoundedCardView(Context context) {
        super(context);
        init(context, null);
    }

    public RoundedCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public RoundedCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.RoundedView, 0, 0);
        try {
            radius = typedArray.getDimension(R.styleable.RoundedView_borderRadius, getResources().getDimension(R.dimen.container_radius));
        } finally {
            typedArray.recycle();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Path clipPath = new Path();
        RectF cardRectF = new RectF(0, 0, this.getWidth(), this.getHeight());
        clipPath.addRoundRect(cardRectF, radius, radius, Path.Direction.CW);
        canvas.clipPath(clipPath);
        super.onDraw(canvas);
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(@DimenRes int radius) {
        this.radius = getResources().getDimension(radius);
    }
}
