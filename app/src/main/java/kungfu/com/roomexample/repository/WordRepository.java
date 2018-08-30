package kungfu.com.roomexample.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import kungfu.com.roomexample.database.Word;
import kungfu.com.roomexample.database.WordDao;
import kungfu.com.roomexample.database.WordRoomDatabase;

/**
 * Created by shubham.srivastava on 24/07/18.
 */

public class WordRepository {

  private WordDao mWordDao;
  private LiveData<List<Word>> mAllWords;


  public WordRepository(Application application) {
    WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
    mWordDao = db.wordDao();
    mAllWords = mWordDao.getAllWords();
  }


  public LiveData<List<Word>> getAllWords() {
    return mAllWords;
  }

  public void insert (Word word) {
    new insertAsyncTask(mWordDao).execute(word);
  }


  private static class insertAsyncTask extends AsyncTask<Word, Void, Void> {

    private WordDao mAsyncTaskDao;

    insertAsyncTask(WordDao dao) {
      mAsyncTaskDao = dao;
    }

    @Override
    protected Void doInBackground(final Word... params) {
      mAsyncTaskDao.insert(params[0]);
      return null;
    }
  }

}
