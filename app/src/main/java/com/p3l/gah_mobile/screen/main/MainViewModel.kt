package com.p3l.gah_mobile.screen.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.p3l.gah_mobile.data.model.availability.AvailabilityKamar
import com.p3l.gah_mobile.data.model.main.DataAkunCustomer
import com.p3l.gah_mobile.data.model.main.FkCustomerReservasiItem
import com.p3l.gah_mobile.data.model.order.DataFasilitas
import com.p3l.gah_mobile.data.model.reservasi.ResponseTandaTerima
import com.p3l.gah_mobile.repository.DataRepository
import com.p3l.gah_mobile.repository.MainRepository
import com.p3l.gah_mobile.util.Resource
import com.p3l.gah_mobile.util.totalNights
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val dataRepository: DataRepository
) : ViewModel(){
    private val _customerInformationState : MutableStateFlow<CustomerInformationState> = MutableStateFlow(CustomerInformationState())
    val customerInformationState : MutableStateFlow<CustomerInformationState>
        get() = _customerInformationState

    private val _idModalState : MutableStateFlow<Int> = MutableStateFlow(0)
    val idModalState : MutableStateFlow<Int>
        get() = _idModalState

    private val _token : MutableStateFlow<String> = MutableStateFlow("")
    private val getToken : MutableStateFlow<String>
        get() = _token

    private val _logoutState : MutableStateFlow<LogoutState> = MutableStateFlow(LogoutState())
    val logoutState : MutableStateFlow<LogoutState>
        get() = _logoutState

    private val _changePasswordState : MutableStateFlow<ChangePasswordState> = MutableStateFlow(ChangePasswordState())
    val changePasswordState : MutableStateFlow<ChangePasswordState>
        get() = _changePasswordState

    private val _detailReservasi : MutableStateFlow<DetailReservasi> = MutableStateFlow(
        DetailReservasi()
    )
    val detailReservasi : MutableStateFlow<DetailReservasi>
        get() = _detailReservasi

    private val _availabilityState : MutableStateFlow<DataAvailability> = MutableStateFlow(DataAvailability())
    val availabilityState : MutableStateFlow<DataAvailability>
        get() = _availabilityState

    private val _roomState : MutableStateFlow<List<Room>> = MutableStateFlow(listOf())
    val roomState : MutableStateFlow<List<Room>>
        get() = _roomState

    private val _fasilitasState : MutableStateFlow<DataFasilitasState> = MutableStateFlow(DataFasilitasState())
    val fasilitasState : MutableStateFlow<DataFasilitasState>
        get() = _fasilitasState

    private val _layananState : MutableStateFlow<List<Layanan>> = MutableStateFlow(listOf())
    val layananState : MutableStateFlow<List<Layanan>>
        get() = _layananState

    private val _permintaanKhusus : MutableStateFlow<String> = MutableStateFlow("")
    val permintaanKhusus : StateFlow<String>
        get() = _permintaanKhusus

    private val _stateDataReservasi : MutableStateFlow<DataReservasi> = MutableStateFlow(DataReservasi())
    val stateDataReservasi : StateFlow<DataReservasi>
        get() = _stateDataReservasi

    private val _listReservasi : MutableStateFlow<List<FkCustomerReservasiItem>> = MutableStateFlow(listOf())
    val listReservasi : StateFlow<List<FkCustomerReservasiItem>>
        get() = _listReservasi

    private val _tandaTerimaState : MutableStateFlow<DataTandaTerima> = MutableStateFlow(
        DataTandaTerima()
    )

    val tandaTerimaState : StateFlow<DataTandaTerima>
        get() = _tandaTerimaState

    private val _changeStatusReservasiState : MutableStateFlow<DataChangeStatusReservasi> = MutableStateFlow(DataChangeStatusReservasi())
    val changeStatusReservasiState : StateFlow<DataChangeStatusReservasi>
        get() = _changeStatusReservasiState

    init {
        viewModelScope.launch {
            dataRepository.getToken().collectLatest {
                _token.value = it
                getAkunCustomer(it)
            }
        }
    }

    fun setIdModal(idModal: Int){
        _idModalState.value = idModal
    }

    fun setPermintaanKhusus(permintaanKhusus: String){
        _permintaanKhusus.value = permintaanKhusus
    }

    fun setTanggal(tanggalCheckIn : String, tanggalCheckOut : String){
        _detailReservasi.value = DetailReservasi(tanggalCheckIn, tanggalCheckOut)
    }

    fun setDewasa(dewasa: Int){
        _detailReservasi.value = _detailReservasi.value.copy(dewasa = dewasa)
    }

    fun setAnak(anak: Int){
        _detailReservasi.value = _detailReservasi.value.copy(anak = anak)
    }

    fun setIdReservasi(idReservasi: Int){
        _stateDataReservasi.value = DataReservasi(response = idReservasi)
    }

    private fun getAkunCustomer(token:String){
        mainRepository.getAkunCustomer(token)
            .onEach { result ->
                when(result){
                    is Resource.Success -> {
                        val data = result.data
                        if (data != null) {
                            _customerInformationState.value = CustomerInformationState(response = data.data)
                        }
                    }
                    is Resource.Error -> {
                        _customerInformationState.value = CustomerInformationState(error = result.message!!)
                    }
                    is Resource.Loading -> {
                        _customerInformationState.value = CustomerInformationState(isLoading = true)
                    }
                }
        }.launchIn(viewModelScope)
    }

    fun updateCustomer(
        id: String,
        nama:String,
        email:String,
        noIdentitas:String,
        noTelepon:String,
        alamat:String,
    ){
        viewModelScope.launch {
            mainRepository.updateCustomer(getToken.value ,id, nama, email, noIdentitas, noTelepon, alamat)
                .onEach { result ->
                    when(result){
                        is Resource.Success -> {
                            val data = result.data
                            if (data != null) {
                                _customerInformationState.value = CustomerInformationState(response = data.data)
                            }
                        }
                        is Resource.Error -> {
                            _customerInformationState.value = CustomerInformationState(error = result.message!!)
                        }
                        is Resource.Loading -> {
                            _customerInformationState.value = CustomerInformationState(isLoading = true)
                        }
                    }
                }.launchIn(viewModelScope)
        }
    }

    fun logoutCustomer(){
        viewModelScope.launch {
            mainRepository.logoutCustomer(getToken.value)
                .onEach { result ->
                    when(result){
                        is Resource.Success -> {
                            val data = result.data
                            if (data != null) {
                                _logoutState.value = LogoutState(response = data.message, status = data.status)
                            }
                        }
                        is Resource.Error -> {
                            _logoutState.value = LogoutState(response = result.message!!, status = false)
                        }
                        is Resource.Loading -> {
                            _logoutState.value = LogoutState(isLoading = true)
                        }
                    }
                }.launchIn(viewModelScope)
        }
    }

    fun changePassword(password: String, newPassword : String){
        viewModelScope.launch {
            mainRepository.changePassword(getToken.value, password, newPassword)
                .onEach { result ->
                    when(result){
                        is Resource.Success -> {
                            val data = result.data
                            if (data != null) {
                                _changePasswordState.value = ChangePasswordState(response = data.message, status = true)
                            }
                        }
                        is Resource.Error -> {
                            _changePasswordState.value = ChangePasswordState(response = result.message!!, status = false)
                        }
                        is Resource.Loading -> {
                            _changePasswordState.value = ChangePasswordState(isLoading = true)
                        }
                    }
                }.launchIn(viewModelScope)
        }
    }

    fun checkAvailability(){
        mainRepository.checkAvailability(
                getToken.value,
                detailReservasi.value.tanggalCheckIn,
                detailReservasi.value.tanggalCheckOut,
            )
            .onEach { result ->
                when(result){
                    is Resource.Success -> {
                        val data = result.data
                        if (data != null) {
                            _availabilityState.value = DataAvailability(data = data.data)
                        }
                    }
                    is Resource.Error -> {
                        _availabilityState.value = DataAvailability(error = result.message!!)
                    }
                    is Resource.Loading -> {
                        _availabilityState.value = DataAvailability(isLoading = true)
                    }
                }
            }.launchIn(viewModelScope)
    }


    // Room
    fun addRoom(payload : Room){
        val list = roomState.value.toMutableList()
        list.add(payload)
        _roomState.value = list
    }

    fun removeRoom(payload : Room){
        val list = roomState.value.toMutableList()
        list.remove(payload)
        _roomState.value = list
    }

    fun increaseRoomQuantity(id: Int){
        val list = roomState.value.toMutableList()
        val index = list.indexOfFirst { it.id == id }
        list[index] = list[index].copy(quantity = list[index].quantity + 1, totalPrice = list[index].price * (list[index].quantity + 1))
        _roomState.value = list
    }

    fun decreaseRoomQuantity(id: Int){
        val list = roomState.value.toMutableList()
        val index = list.indexOfFirst { it.id == id }
        list[index] = list[index].copy(quantity = list[index].quantity - 1, totalPrice = list[index].price * (list[index].quantity - 1))

        if(list[index].quantity == 0){
            list.removeAt(index)
        }

        _roomState.value = list
    }

    fun clearRoom(){
        _roomState.value = listOf()
    }

    fun getCurrentQuantityById(id : Int) : Int{
        val list = roomState.value.toMutableList()
        val index = list.indexOfFirst { it.id == id }

        if(index == -1) return 0

        return list[index].quantity
    }

    fun getFasilitas(){
            mainRepository.getFasilitas(getToken.value)
                .onEach { result ->
                    when(result){
                        is Resource.Success -> {
                            val data = result.data
                            if (data != null) {
                                _fasilitasState.value = DataFasilitasState(data = data.data)
                            }
                        }
                        is Resource.Error -> {
                            _fasilitasState.value = DataFasilitasState(error = result.message!!)
                        }
                        is Resource.Loading -> {
                            _fasilitasState.value = DataFasilitasState(isLoading = true)
                        }
                    }
                }.launchIn(viewModelScope)
    }

    fun addLayanan(payload : Layanan){
        val list = layananState.value.toMutableList()
        list.add(payload)
        _layananState.value = list
    }

    fun removeLayanan(payload : Layanan){
        val list = layananState.value.toMutableList()
        list.remove(payload)
        _layananState.value = list
    }

    fun increaseLayananQuantity(id: Int){
        val list = layananState.value.toMutableList()
        val index = list.indexOfFirst { it.id == id }
        list[index] = list[index].copy(quantity = list[index].quantity + 1, totalPrice = list[index].price * (list[index].quantity + 1))
        _layananState.value = list
    }

    fun decreaseLayananQuantity(id: Int){
        val list = layananState.value.toMutableList()
        val index = list.indexOfFirst { it.id == id }
        list[index] = list[index].copy(quantity = list[index].quantity - 1, totalPrice = list[index].price * (list[index].quantity - 1))

        if(list[index].quantity == 0){
            list.removeAt(index)
        }

        _layananState.value = list
    }

    fun getCurrentLayananQuantityById(id : Int) : Int{
        val list = layananState.value.toMutableList()
        val index = list.indexOfFirst { it.id == id }

        if(index == -1) return 0

        return list[index].quantity
    }

    fun clearLayanan(){
        _layananState.value = listOf()
    }

    fun createReservasi(){
        val totalPembayaran = roomState.value.sumOf { it.totalPrice } * totalNights(detailReservasi.value.tanggalCheckIn, detailReservasi.value.tanggalCheckOut)
        val kamars = roomState.value.flatMap { item ->
            List(item.quantity) { item.id }
        }
        mainRepository.createReservasi(
            token = getToken.value,
            idCustomer = customerInformationState.value.response.idUser,
            checkIn = detailReservasi.value.tanggalCheckIn,
            checkOut = detailReservasi.value.tanggalCheckOut,
            dewasa = detailReservasi.value.dewasa,
            anak = detailReservasi.value.anak,
            totalPembayaran = totalPembayaran.toInt(),
            permintaan = if(permintaanKhusus.value.isEmpty()){
                            "-"
                        }else{
                            permintaanKhusus.value
                         },
            kamars = kamars,
            layanans = layananState.value
        )
            .onEach { result ->
                when(result){
                    is Resource.Success -> {
                        val data = result.data
                        if (data != null) {
                            _stateDataReservasi.value = DataReservasi(response = data.data.idReservasi)
                        }
                    }
                    is Resource.Error -> {
                        _stateDataReservasi.value = DataReservasi(error = result.message!!)
                    }
                    is Resource.Loading -> {
                        _stateDataReservasi.value = DataReservasi(isLoading = true)
                    }
                }
            }.launchIn(viewModelScope)
    }

    fun getTandaTerima(){
        mainRepository.getTandaTerima(getToken.value, stateDataReservasi.value.response)
            .onEach { result ->
                when(result){
                    is Resource.Success -> {
                        val data = result.data
                        if (data != null) {
                            _tandaTerimaState.value = DataTandaTerima(response = data)
                        }
                    }
                    is Resource.Error -> {
                        _tandaTerimaState.value = DataTandaTerima(error = result.message!!)
                    }
                    is Resource.Loading -> {
                        _tandaTerimaState.value = DataTandaTerima(isLoading = true)
                    }
                }
            }.launchIn(viewModelScope)
    }

    fun changeStatusReservasi(status: String, idReservasi: Int = 0){
        val idReservasiReal = if(idReservasi == 0) stateDataReservasi.value.response else idReservasi
        mainRepository.changeStatusReservasi(getToken.value, idReservasiReal, status)
            .onEach { result ->
                when(result){
                    is Resource.Success -> {
                        val data = result.data
                        if (data != null) {
                            _changeStatusReservasiState.value = DataChangeStatusReservasi(response = data.message)
                            getAkunCustomer(getToken.value)
                        }
                    }
                    is Resource.Error -> {
                        _changeStatusReservasiState.value = DataChangeStatusReservasi(error = result.message!!)
                    }
                    is Resource.Loading -> {
                        _changeStatusReservasiState.value = DataChangeStatusReservasi(isLoading = true)
                    }
                }
            }.launchIn(viewModelScope)
    }

    fun filterTransaksi(value: String){
        val list = customerInformationState.value.response.fkCustomerReservasi.filter {
            it.status.contains(value, ignoreCase = true)|| it.idBooking.contains(value, ignoreCase = true)
        }
        _listReservasi.value = list
    }


}

data class CustomerInformationState(
    val response: DataAkunCustomer = DataAkunCustomer(),
    val isLoading: Boolean = false,
    val error: String = ""
)

data class LogoutState(
    val response: String = "",
    val isLoading: Boolean = false,
    val status: Boolean = false,
)

data class ChangePasswordState(
    val response: String = "",
    val isLoading: Boolean = false,
    val status: Boolean = false,
)

data class DataAvailability(
    val data: List<AvailabilityKamar> = listOf(),
    val isLoading: Boolean = false,
    val error: String = ""
)

data class Room(
    val id: Int = 0,
    val image : String = "",
    val jenisKamar : String = "",
    val price : Int = 0,
    val quantity : Int = 0,
    val totalPrice : Int = 0,
)

data class DataFasilitasState(
    val data: List<DataFasilitas> = listOf(),
    val isLoading: Boolean = false,
    val error: String = ""
)

data class DetailReservasi(
    val tanggalCheckIn: String = "",
    val tanggalCheckOut: String = "",
    val anak : Int = 0,
    val dewasa : Int = 0,
)

data class Layanan(
    val id : Int = 0,
    val nama_layanan : String = "",
    val price : Int = 0,
    val quantity : Int = 0,
    val totalPrice : Int = 0,
)

data class DataReservasi(
    val isLoading: Boolean = false,
    val error: String = "",
    val response: Int = 0
)

data class DataTandaTerima(
    val isLoading: Boolean = false,
    val error: String = "",
    val response: ResponseTandaTerima = ResponseTandaTerima()
)

data class DataChangeStatusReservasi(
    val isLoading: Boolean = false,
    val error: String = "",
    val response: String = ""
)
