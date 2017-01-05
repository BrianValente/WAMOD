package com.wamod.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.*;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

/**
 * Created by brianvalente on 12/16/16.
 */
public class CircularImageView extends ImageView {
    private boolean hasBorder;
    private boolean hasSelector;
    private boolean isSelected;
    private int borderWidth;
    private int canvasSize;
    private int selectorStrokeWidth;
    private BitmapShader shader;
    private Bitmap image;
    private Paint paint;
    private Paint paintBorder;
    private Paint paintSelectorBorder;
    private ColorFilter selectorFilter;

    public CircularImageView(Context context) {
        this(context, null);
    }

    public CircularImageView(Context context, AttributeSet attrs) {
        this(context, attrs, com.pkmmte.view.R.attr.circularImageViewStyle);
    }

    public CircularImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.init(context, attrs, defStyle);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        this.paint = new Paint();
        this.paint.setAntiAlias(true);
        this.paintBorder = new Paint();
        this.paintBorder.setAntiAlias(true);
        this.paintSelectorBorder = new Paint();
        this.paintSelectorBorder.setAntiAlias(true);
        TypedArray attributes = context.obtainStyledAttributes(attrs, com.pkmmte.view.R.styleable.CircularImageView, defStyle, 0);
        this.hasBorder = attributes.getBoolean(0, false);
        this.hasSelector = attributes.getBoolean(3, false);
        int defaultSelectorSize;
        if(this.hasBorder) {
            defaultSelectorSize = (int)(2.0F * context.getResources().getDisplayMetrics().density + 0.5F);
            this.setBorderWidth(attributes.getDimensionPixelOffset(2, defaultSelectorSize));
            this.setBorderColor(attributes.getColor(1, -1));
        }

        if(this.hasSelector) {
            defaultSelectorSize = (int)(2.0F * context.getResources().getDisplayMetrics().density + 0.5F);
            this.setSelectorColor(attributes.getColor(4, 0));
            this.setSelectorStrokeWidth(attributes.getDimensionPixelOffset(6, defaultSelectorSize));
            this.setSelectorStrokeColor(attributes.getColor(5, -16776961));
        }

        if(attributes.getBoolean(7, false)) {
            this.addShadow();
        }

        attributes.recycle();
    }

    public void setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
        this.requestLayout();
        this.invalidate();
    }

    public void setBorderColor(int borderColor) {
        if(this.paintBorder != null) {
            this.paintBorder.setColor(borderColor);
        }

        this.invalidate();
    }

    public void setSelectorColor(int selectorColor) {
        this.selectorFilter = new PorterDuffColorFilter(selectorColor, PorterDuff.Mode.SRC_ATOP);
        this.invalidate();
    }

    public void setSelectorStrokeWidth(int selectorStrokeWidth) {
        this.selectorStrokeWidth = selectorStrokeWidth;
        this.requestLayout();
        this.invalidate();
    }

    public void setSelectorStrokeColor(int selectorStrokeColor) {
        if(this.paintSelectorBorder != null) {
            this.paintSelectorBorder.setColor(selectorStrokeColor);
        }

        this.invalidate();
    }

    public void addShadow() {
        this.setLayerType(1, this.paintBorder);
        this.paintBorder.setShadowLayer(4.0F, 0.0F, 2.0F, -16777216);
    }

    public void onDraw(Canvas canvas) {
        if(this.image != null) {
            if(this.image.getHeight() != 0 && this.image.getWidth() != 0) {
                int oldCanvasSize = this.canvasSize;
                this.canvasSize = canvas.getWidth();
                if(canvas.getHeight() < this.canvasSize) {
                    this.canvasSize = canvas.getHeight();
                }

                if(oldCanvasSize != this.canvasSize) {
                    this.refreshBitmapShader();
                }

                this.paint.setShader(this.shader);
                int outerWidth = 0;
                int center = this.canvasSize / 2;
                if(this.hasSelector && this.isSelected) {
                    outerWidth = this.selectorStrokeWidth;
                    center = (this.canvasSize - outerWidth * 2) / 2;
                    this.paint.setColorFilter(this.selectorFilter);
                    canvas.drawCircle((float)(center + outerWidth), (float)(center + outerWidth), (float)((this.canvasSize - outerWidth * 2) / 2 + outerWidth) - 4.0F, this.paintSelectorBorder);
                } else if(this.hasBorder) {
                    outerWidth = this.borderWidth;
                    center = (this.canvasSize - outerWidth * 2) / 2;
                    this.paint.setColorFilter((ColorFilter)null);
                    canvas.drawCircle((float)(center + outerWidth), (float)(center + outerWidth), (float)((this.canvasSize - outerWidth * 2) / 2 + outerWidth) - 4.0F, this.paintBorder);
                } else {
                    this.paint.setColorFilter((ColorFilter)null);
                }

                canvas.drawCircle((float)(center + outerWidth), (float)(center + outerWidth), (float)((this.canvasSize - outerWidth * 2) / 2) - 4.0F, this.paint);
            }
        }
    }

    public boolean dispatchTouchEvent(MotionEvent event) {
        if(!this.isClickable()) {
            this.isSelected = false;
            return super.onTouchEvent(event);
        } else {
            switch(event.getAction()) {
                case 0:
                    this.isSelected = true;
                    break;
                case 1:
                case 3:
                case 4:
                case 8:
                    this.isSelected = false;
                case 2:
                case 5:
                case 6:
                case 7:
            }

            this.invalidate();
            return super.dispatchTouchEvent(event);
        }
    }

    public void invalidate(Rect dirty) {
        super.invalidate(dirty);
        this.image = this.drawableToBitmap(this.getDrawable());
        if(this.shader != null || this.canvasSize > 0) {
            this.refreshBitmapShader();
        }

    }

    public void invalidate(int l, int t, int r, int b) {
        super.invalidate(l, t, r, b);
        this.image = this.drawableToBitmap(this.getDrawable());
        if(this.shader != null || this.canvasSize > 0) {
            this.refreshBitmapShader();
        }

    }

    public void invalidate() {
        super.invalidate();
        this.image = this.drawableToBitmap(this.getDrawable());
        if(this.shader != null || this.canvasSize > 0) {
            this.refreshBitmapShader();
        }

    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = this.measureWidth(widthMeasureSpec);
        int height = this.measureHeight(heightMeasureSpec);
        this.setMeasuredDimension(width, height);
    }

    private int measureWidth(int measureSpec) {
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        int result;
        if(specMode == 1073741824) {
            result = specSize;
        } else if(specMode == -2147483648) {
            result = specSize;
        } else {
            result = this.canvasSize;
        }

        return result;
    }

    private int measureHeight(int measureSpecHeight) {
        int specMode = MeasureSpec.getMode(measureSpecHeight);
        int specSize = MeasureSpec.getSize(measureSpecHeight);
        int result1;
        if(specMode == 1073741824) {
            result1 = specSize;
        } else if(specMode == -2147483648) {
            result1 = specSize;
        } else {
            result1 = this.canvasSize;
        }

        return result1 + 2;
    }

    public Bitmap drawableToBitmap(Drawable drawable) {
        if(drawable == null) {
            return null;
        } else if(drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable)drawable).getBitmap();
        } else {
            Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return bitmap;
        }
    }

    public void refreshBitmapShader() {
        this.shader = new BitmapShader(Bitmap.createScaledBitmap(this.image, this.canvasSize, this.canvasSize, false), Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
    }

    public boolean isSelected() {
        return this.isSelected;
    }
}
