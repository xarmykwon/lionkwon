package com.lionkwon.kwonutils.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lionkwon.kwonutils.R;

public class CDialog extends Dialog {

	Context context;

	public CDialog(Context context) {
        super(context);
        this.context = context;
    }
    
	public CDialog(Context context, int theme) {
        super(context, theme);
        this.context = context;
    }

    public static class Builder {

        private final Context context;
        private String title;
        private String message;
        private String positiveButtonText;
        private String negativeButtonText;
        private View contentView;

        public String who;
        private DialogInterface.OnClickListener positiveButtonClickListener, negativeButtonClickListener;

        public Builder(Context context, String who) {
            this.context = context;
            this.who = who;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setMessage(int message) {
            this.message = (String) context.getText(message);
            return this;
        }

        public Builder setTitle(int title) {
            this.title = (String) context.getText(title);
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setContentView(View v) {
            this.contentView = v;
            return this;
        }

        public Builder setPositiveButton(int positiveButtonText, DialogInterface.OnClickListener listener) {
            this.positiveButtonText = (String) context.getText(positiveButtonText);
            this.positiveButtonClickListener = listener;
            return this;
        }

        public Builder setPositiveButton(String positiveButtonText, DialogInterface.OnClickListener listener) {
            this.positiveButtonText = positiveButtonText;
            this.positiveButtonClickListener = listener;
            return this;
        }

        public Builder setNegativeButton(int negativeButtonText, DialogInterface.OnClickListener listener) {
            this.negativeButtonText = (String) context.getText(negativeButtonText);
            this.negativeButtonClickListener = listener;
            return this;
        }

        public Builder setNegativeButton(String negativeButtonText, DialogInterface.OnClickListener listener) {
            this.negativeButtonText = negativeButtonText;
            this.negativeButtonClickListener = listener;
            return this;
        }

        public CDialog create() {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            CDialog dialog2 = null;
            if(who.equals("baby")){
            	dialog2 = new CDialog(context, R.style.BabyDialogTheme);
            }else if(who.equals("mom")){
            	dialog2 = new CDialog(context, R.style.MomDialogTheme);
            }else{
            	dialog2 = new CDialog(context, R.style.MDialogTheme);
            }
            final CDialog dialog = dialog2;
            View layout = inflater.inflate(R.layout.cdialog, null);
            dialog.addContentView(layout, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            ((TextView) layout.findViewById(R.id.title)).setText(title);
            if (positiveButtonText != null) {
                ((Button) layout.findViewById(R.id.but_positive)).setText(positiveButtonText);
                if(who.equals("baby")){
//                	Button posi = (Button)layout.findViewById(R.id.but_positive);
//                	posi.setBackgroundResource(R.drawable.popup_baby_ok);
                	layout.findViewById(R.id.but_positive).setBackgroundResource(R.drawable.popup_baby_ok);
                }else if(who.equals("mom")){
                	layout.findViewById(R.id.but_positive).setBackgroundResource(R.drawable.mother_popup_ok);
                }else{

                }

                if (positiveButtonClickListener != null) {
                    ((Button) layout.findViewById(R.id.but_positive)).setOnClickListener(new View.OnClickListener() {
                    	@Override
						public void onClick(View v) {
                    		positiveButtonClickListener.onClick(dialog, DialogInterface.BUTTON_POSITIVE);
                    	}
                    });
                }
            } else {
                layout.findViewById(R.id.but_positive).setVisibility(View.GONE);
            }
            if (negativeButtonText != null) {
                ((Button) layout.findViewById(R.id.but_negative)).setText(negativeButtonText);
                if(who.equals("baby")){
//                	Button posi = (Button)layout.findViewById(R.id.but_positive);
//                	posi.setBackgroundResource(R.drawable.popup_baby_ok);
                	layout.findViewById(R.id.but_negative).setBackgroundResource(R.drawable.popup_baby_cancel);
                }else if(who.equals("mom")){
                	layout.findViewById(R.id.but_negative).setBackgroundResource(R.drawable.mother_popup_cancel);
                }else{

                }
                if (negativeButtonClickListener != null) {
                    ((Button) layout.findViewById(R.id.but_negative)).setOnClickListener(new View.OnClickListener() {
                    	@Override
						public void onClick(View v) {
                    		negativeButtonClickListener.onClick(dialog, DialogInterface.BUTTON_NEGATIVE);
                    	}
                    });
                }
            } else {
                layout.findViewById(R.id.but_negative).setVisibility(View.GONE);
            }
            if (message != null) {
                ((TextView) layout.findViewById(R.id.message)).setText(message);
            } else if (contentView != null) {
                ((LinearLayout) layout.findViewById(R.id.content)).removeAllViews();
                ((LinearLayout) layout.findViewById(R.id.content)).addView(contentView,new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
            }
            dialog.setContentView(layout);
            return dialog;
        }
    }
    
    public void howtouse(){
    	CDialog.Builder customBuilder = new CDialog.Builder(context, "main");
		customBuilder.setTitle("종료")
		.setMessage("프로그램 종료")
		.setNegativeButton("", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface d, int which) {
				d.dismiss();
			}
		})
		.setPositiveButton("",  new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface d, int which) {
				d.dismiss();
			}
		});
		customBuilder.create().show();
    }

}
