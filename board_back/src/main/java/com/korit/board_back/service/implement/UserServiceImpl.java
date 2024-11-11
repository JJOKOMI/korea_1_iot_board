package com.korit.board_back.service.implement;

import com.korit.board_back.common.ResponseMessage;
import com.korit.board_back.dto.ResponseDto;
import com.korit.board_back.dto.user.request.UpdateUserRequestDto;
import com.korit.board_back.dto.user.response.UserResponseDto;
import com.korit.board_back.entity.User;
import com.korit.board_back.repository.UserRepository;
import com.korit.board_back.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/*
    Implement의 축약어 : impl
 */
@Service
@RequiredArgsConstructor // 의존성 주입
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public ResponseDto<UserResponseDto> getUserInfo(String userId) {
        UserResponseDto data = null;

        try {
            User user = userRepository.findByUserId(userId)
                    .orElse(null);

            if(user == null) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER);
            }

            data = new UserResponseDto(user);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<UserResponseDto> updateUser(String userID, UpdateUserRequestDto dto) {
        UserResponseDto data = null;
        String email = dto.getEmail();
        String name = dto.getName();
        String phone = dto.getPhone();

        try {
            User user = userRepository.findByUserId(userID)
                    .orElse(null);

            if(user == null) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER);
            }

//            user.setEmail(email);
//            user.setName(name);
//            user.setPhone(phone);

            //객체.toBuilder은 이미 생성된 클래스에서 수정하는거/ 그냥 빌더는 처음 인스턴스화 하는거
            user =user.toBuilder() // 빌더 - 생성자. 필수매개변수랑 선택매개변수 지정가능. 수정할때 빌더사용하는것이 좋음
                    .email(email)
                    .name(name)
                    .phone(phone)
                    .build();

            userRepository.save(user);
            data = new UserResponseDto(user);

        } catch(Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<Void> deleteUser(String userId) {
        try {
            User user = userRepository.findByUserId(userId)
                    .orElse(null);
            if(user == null) {
                ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER);
            }
            userRepository.delete(user);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, null);
    }
}


















