package ir.pearly.pershiandate;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog;
import com.mohamadamin.persianmaterialdatetimepicker.time.RadialPickerLayout;
import com.mohamadamin.persianmaterialdatetimepicker.time.TimePickerDialog;
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar;

import java.util.Calendar;

import ir.hamsaa.persiandatepicker.Listener;
import ir.hamsaa.persiandatepicker.PersianDatePickerDialog;

/**
 * Created by Administrator on 4/24/2018.
 */

//compile 'com.mohamadamin:persianmaterialdatetimepicker:1.2.1'

// new persian
//    compile 'com.github.hamsaadev:Persian-Date-Picker-Dialog:V1.2' //shamsi
//    allprojects {
//            repositories {
//            maven { url "https://jitpack.io" }
//            }
//            }

public class AlaviDatePicker {
    public int year, month, day, hour, minute;
    public boolean cancel;
    public String stringDate;
    private Activity activity;
    private boolean ispersian;
    private AlaviPershianDate alaviPershianDate;

    public AlaviDatePicker(Activity activity) {
        this.activity = activity;
        alaviPershianDate = new AlaviPershianDate();
    }

    public static final Handler handler = new Handler() {

        @Override
        public void handleMessage(Message mesg) {
            throw new RuntimeException();
        }
    };

    public static void waitstart() {
        try {


            Looper.loop();
        } catch (RuntimeException e) {
        }
    }

    public static void waitstop() {
        handler.sendMessage(handler.obtainMessage());
    }

    public long getTime() {
        if (ispersian) {
            alaviPershianDate.setIranianDate(year, month, day);
        } else {
            alaviPershianDate.setGregorianDate(year, month, day);
        }
        return alaviPershianDate.getTime();
    }

    public void showPersianDatePicker() {
        cancel = true;
        PersianCalendar now = new PersianCalendar();
        year = now.getPersianYear();
        month = now.getPersianMonth();
        day = now.getPersianDay();

        DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(new DatePickerDialog.OnDateSetListener() {


            @Override
            public void onDateSet(DatePickerDialog view, int pyear, int monthOfYear, int dayOfMonth) {
                year = pyear;
                month = monthOfYear + 1;
                day = dayOfMonth;
                cancel = false;
                ispersian = true;
            }
        }, year, month, day);

//        datePickerDialog.setThemeDark(true);
        datePickerDialog.setYearRange(1250, 1450);
        datePickerDialog.show(activity.getFragmentManager(), "tpd");
        datePickerDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                waitstop();
            }
        });
        waitstart();
    }

    public void showPersianDatePicker(int iyear, int imonth, int iday) {
        year = iyear;
        month = imonth - 1;
        day = iday;
        cancel = true;

        DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(new DatePickerDialog.OnDateSetListener() {


            @Override
            public void onDateSet(DatePickerDialog view, int pyear, int monthOfYear, int dayOfMonth) {
                year = pyear;
                month = monthOfYear + 1;
                day = dayOfMonth;
                cancel = false;
                ispersian = true;
            }
        }, year, month, day);

        datePickerDialog.setYearRange(1250, 1450);
        datePickerDialog.show(activity.getFragmentManager(), "tpd");
        datePickerDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                waitstop();
            }
        });

        waitstart();
    }

    public void showPersianTimePicker() {
        cancel = true;
        Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);


        TimePickerDialog timePickerDialog = new TimePickerDialog();
        timePickerDialog.initialize(new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(RadialPickerLayout view, int hourOfDay, int pminute) {
                hour = hourOfDay;
                minute = pminute;
                cancel = false;
            }
        }, hour, minute, true);
        timePickerDialog.show(activity.getFragmentManager(), "tfm");
        timePickerDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                waitstop();
            }
        });
        waitstart();
    }

    public void showPersianTimePicker(int ihour, int iminute) {


        hour = ihour;
        minute = iminute;
        cancel = true;

        TimePickerDialog timePickerDialog = new TimePickerDialog();
        timePickerDialog.initialize(new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(RadialPickerLayout view, int hourOfDay, int pminute) {
                hour = hourOfDay;
                minute = pminute;
                cancel = false;
            }
        }, hour, minute, true);
        timePickerDialog.show(activity.getFragmentManager(), "tfm");
        timePickerDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                waitstop();
            }
        });
        waitstart();
    }

    public void showPersianiDatePickerOld() {
        cancel = true;
        PersianDatePickerDialog picker2 = new PersianDatePickerDialog(activity)
                .setPositiveButtonString("باشه")
                .setNegativeButton("بیخیال")
//                .setTypeFace(iran)
                .setActionTextColor(Color.GRAY)
                .setMaxYear(1500)
                .setMinYear(1300)
                .setListener(new Listener() {
                    @Override
                    public void onDateSelected(ir.hamsaa.persiandatepicker.util.PersianCalendar persianCalendar) {
                        year = persianCalendar.getPersianYear();
                        month = persianCalendar.getPersianMonth();
                        day = persianCalendar.getPersianDay();
                        cancel = false;
                        ispersian = true;
                        waitstop();
                    }

                    @Override
                    public void onDismissed() {

                    }
                });


        picker2.show();
        waitstart();
    }

    public void showPersianiDatePickerOld(int iyear, int imonth, int iday) {
        year = iyear;
        month = imonth;
        day = iday;
        cancel = true;
        ir.hamsaa.persiandatepicker.util.PersianCalendar persianCalendar = new ir.hamsaa.persiandatepicker.util.PersianCalendar();
        persianCalendar.setPersianDate(year, month, day);

        PersianDatePickerDialog picker2 = new PersianDatePickerDialog(activity)
                .setPositiveButtonString("باشه")
                .setNegativeButton("بیخیال")
                .setInitDate(persianCalendar)
                .setActionTextColor(Color.GRAY)

                .setMaxYear(1500)
                .setMinYear(1300)
                .setListener(new Listener() {
                    @Override
                    public void onDateSelected(ir.hamsaa.persiandatepicker.util.PersianCalendar persianCalendar) {
//                        Toast.makeText(MainActivity.this, persianCalendar.getPersianYear() + "/" + persianCalendar.getPersianMonth() + "/" + persianCalendar.getPersianDay(), Toast.LENGTH_SHORT).show();
                        year = persianCalendar.getPersianYear();
                        month = persianCalendar.getPersianMonth();
                        day = persianCalendar.getPersianDay();
                        cancel = false;
                        ispersian = true;
                        waitstop();
                    }

                    @Override
                    public void onDismissed() {

                    }

                });

        picker2.show();
        waitstart();
    }

    public void showMiladiDatePickerOld() {
        cancel = true;
        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
//        text1.setText(mDay + "/" + (mMonth+1) + "/" + mYear);


        android.app.DatePickerDialog datePickerDialog = new android.app.DatePickerDialog(activity, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                new android.app.DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int myear,
                                          int monthOfYear, int dayOfMonth) {
                        year = myear;
                        month = monthOfYear + 1;
                        day = dayOfMonth;
                        cancel = false;
                        ispersian = false;
                    }
                }, year, month, day);

        datePickerDialog.show();

        datePickerDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                cancel = true;
            }
        });
        datePickerDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                waitstop();
            }
        });

        waitstart();
    }

    public void showMiladiDatePickerOld(int iyear, int imonth, int iday) {
        cancel = true;

        year = iyear;
        month = imonth - 1;
        day = iday;
//        text1.setText(mDay + "/" + (mMonth+1) + "/" + mYear);


        android.app.DatePickerDialog datePickerDialog = new android.app.DatePickerDialog(activity, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                new android.app.DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int myear,
                                          int monthOfYear, int dayOfMonth) {
                        year = myear;
                        month = monthOfYear + 1;
                        day = dayOfMonth;
                        cancel = false;
                        ispersian = false;
                    }
                }, year, month, day);
        datePickerDialog.show();
        datePickerDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                cancel = true;
            }
        });
        datePickerDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                waitstop();
            }
        });
        waitstart();
    }

    public void showMiladiDatePicker() {
        cancel = true;
        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
//        text1.setText(mDay + "/" + (mMonth+1) + "/" + mYear);


        android.app.DatePickerDialog datePickerDialog = new android.app.DatePickerDialog(activity,
                new android.app.DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int myear,
                                          int monthOfYear, int dayOfMonth) {
                        year = myear;
                        month = monthOfYear + 1;
                        day = dayOfMonth;
                        cancel = false;
                        ispersian = false;
                    }
                }, year, month, day);

        datePickerDialog.show();

        datePickerDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                cancel = true;
            }
        });
        datePickerDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                waitstop();
            }
        });

        waitstart();
    }

    public void showMiladiDatePicker(int iyear, int imonth, int iday) {
        cancel = true;

        year = iyear;
        month = imonth - 1;
        day = iday;


        android.app.DatePickerDialog datePickerDialog = new android.app.DatePickerDialog(activity,
                new android.app.DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int myear,
                                          int monthOfYear, int dayOfMonth) {
                        year = myear;
                        month = monthOfYear + 1;
                        day = dayOfMonth;
                        cancel = false;
                        ispersian = false;
                    }
                }, year, month, day);
        datePickerDialog.show();
        datePickerDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                cancel = true;
            }
        });
        datePickerDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                waitstop();
            }
        });
        waitstart();
    }

    public void showMiladiTimePicker() {
        cancel = true;
        Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);
        android.app.TimePickerDialog timePickerDialog = new android.app.TimePickerDialog(activity, new android.app.TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int pminute) {
                hour = hourOfDay;
                minute = pminute;
                cancel = false;
            }
        }, hour, minute, true);
        timePickerDialog.show();
        timePickerDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                cancel = true;
            }
        });
        timePickerDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                waitstop();
            }
        });
        waitstart();
    }

    public void showMiladiTimePicker(int ihour, int iminute) {
        cancel = true;

        hour = ihour;
        minute = iminute;
        android.app.TimePickerDialog timePickerDialog = new android.app.TimePickerDialog(activity, new android.app.TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int pminute) {
                hour = hourOfDay;
                minute = pminute;
                cancel = false;
            }
        }, hour, minute, true);
        timePickerDialog.show();
        timePickerDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                cancel = true;
            }
        });
        timePickerDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                waitstop();
            }
        });
        waitstart();
    }

}
