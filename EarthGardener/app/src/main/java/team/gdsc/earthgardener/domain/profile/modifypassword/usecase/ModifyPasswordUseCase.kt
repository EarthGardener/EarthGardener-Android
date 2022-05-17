package team.gdsc.earthgardener.domain.profile.modifypassword.usecase

import team.gdsc.earthgardener.data.model.request.password.ReqModifyPasswordSuccessData
import team.gdsc.earthgardener.domain.profile.modifypassword.ModifyPasswordData
import team.gdsc.earthgardener.domain.profile.modifypassword.ModifyPasswordRepository


class ModifyPasswordUseCase(private val modifyPasswordRepository: ModifyPasswordRepository) {
    suspend operator fun invoke(data: ReqModifyPasswordSuccessData): ModifyPasswordData {
        return modifyPasswordRepository.putModifyPasswordResult(data)
    }
}