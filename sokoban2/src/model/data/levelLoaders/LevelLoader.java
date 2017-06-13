package model.data.levelLoaders;

import java.io.InputStream;

import model.data.Level;

public interface LevelLoader {
	/**
	 * א. יוצר המידע שהוא מנוהל במחלקה נפרדת והוא יוצר לנו מופע של מחזיק המידע
	 * ב. כי אם נרצה להוסיף יוצרי מידע חדשים נוכל לעשות זאת מבלי לשנות כלום בקוד שכבר כתוב
	 * ג. לא משנה באיזה יוצר מידע נשתמש כולם מייצרים לנו בסוף אותו סוג של מופע בעזרת אותה חתימה של פונקציה, כל אחד בדרכו שלו
	 * ד. Input Stream is the superclass of all classes representing an input stream of bytes.
	 */
	public Level loadLevel(InputStream in);
}