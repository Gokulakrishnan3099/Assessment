package com.matrimony.assesment.view.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.matrimony.assesment.data.persistence.entity.User
import com.matrimony.assesment.data.repository.DataRepository
import kotlinx.coroutines.launch

class MyViewModel(application: Application): AndroidViewModel(application) {
    private val repository: DataRepository by lazy {
        DataRepository(application)
    }

    fun insert(user: User) {
        viewModelScope.launch {
            repository.insert(user)
        }
    }
    fun list(): LiveData<List<User>> {
        return repository.list()
    }
    fun getUser(id: Int): LiveData<User> {
        return repository.getUser(id)
    }
    fun loadDummy() {
        viewModelScope.launch {
             if(repository.count() == 0){
                 repository.insert(User(0, "Priyanka", "https://wallpapercave.com/wp/wp4172970.jpg", 27, "5 ft 2 in", "Tamil", "Nair","MBBS, Doctor","Chennai, Tamilnadu, India"))
                 repository.insert(User(0, "Aishwarya", "https://wallpapercave.com/dwp1x/wp4172973.jpg", 26, "5 ft 2 in", "Tamil", "Nair","MBBS, Doctor","Chennai, Tamilnadu, India"))
                 repository.insert(User(0, "Pragathi", "https://wallpapercave.com/dwp1x/wp5514097.jpg", 27, "5 ft 5 in", "Tamil", "Poosam","MBBS, Doctor","Chennai, Tamilnadu, India"))
                 repository.insert(User(0, "Shalini", "https://wallpapercave.com/dwp1x/wp7781270.jpg", 27, "5 ft 5 in", "Tamil", "Poosam","MBBS, Doctor","Chennai, Tamilnadu, India"))
                 repository.insert(User(0, "Priya", "https://wallpapercave.com/wp/wp3991867.jpg", 27, "5 ft 5 in", "Tamil", "Poosam","MBBS, Doctor","Chennai, Tamilnadu, India"))
             }
        }
    }
}