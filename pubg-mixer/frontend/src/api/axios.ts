import axios, { AxiosError, AxiosRequestConfig } from "axios";
import type { CommonResponse } from "../types/common";

export const api = axios.create({
  baseURL: import.meta.env.VITE_API_URL,
  timeout: 8000,
  headers: {
    "Content-Type": "application/json",
  },
});

api.interceptors.request.use((config) => {
  // 확장이 필요할 경우(예: 인증 토큰) 여기서 처리
  return config;
});

api.interceptors.response.use(
  (response) => response,
  (error: AxiosError<CommonResponse<unknown>>) => {
    // 공통 에러 로깅/전처리 지점
    return Promise.reject(error);
  },
);

export async function request<T>(
  config: AxiosRequestConfig,
): Promise<CommonResponse<T>> {
  const response = await api.request<CommonResponse<T>>(config);
  return response.data;
}