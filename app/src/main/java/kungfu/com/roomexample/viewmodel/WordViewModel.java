package kungfu.com.roomexample.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import kungfu.com.roomexample.database.Word;
import kungfu.com.roomexample.repository.WordRepository;

/**
 * The ViewModel is a class whose role is to provide data to the UI and survive configuration changes.
 * Never pass context into ViewModel instances. Do not store Activity, Fragment, or View instances or their Context in the ViewModel.
 * An Activity can be destroyed and created many times during the lifecycle of a ViewModel, such as when the device is rotated.
 * If you store a reference to the Activity in the ViewModel, you end up with references that point to the destroyed Activity.
 * This is a memory leak.
 */

public class WordViewModel extends AndroidViewModel {

  private WordRepository mRepository;

  private LiveData<List<Word>> mAllWords;

  public WordViewModel(@NonNull Application application) {
    super(application);
    mRepository = new WordRepository(application);
    mAllWords = mRepository.getAllWords();
  }

  public LiveData<List<Word>> getAllWords() { return mAllWords; }

  public void insert(Word word) { mRepository.insert(word); }
}
