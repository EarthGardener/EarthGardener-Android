package team.gdsc.earthgardener.domain.profile.modifyprofile.usecase

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Part
import retrofit2.http.PartMap
import team.gdsc.earthgardener.domain.profile.modifyprofile.ModifyProfileData
import team.gdsc.earthgardener.domain.profile.modifyprofile.ModifyProfileRepository

class ModifyProfileUseCase(private val modifyProfileRepository: ModifyProfileRepository) {
    suspend operator fun invoke(image: MultipartBody.Part, data: HashMap<String, RequestBody>) : ModifyProfileData{
        return modifyProfileRepository.putModifyProfileResult(image, data)
    }
}