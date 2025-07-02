package com.example.CodeJudge.modelDTOs;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavouriteDTO {
    @NotNull
    private Long userId;
    @NotNull
    private Long problemId;
}
