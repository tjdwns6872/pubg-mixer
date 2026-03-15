export interface CommonResponse<T> {

  success: boolean
  data: T
  message?: string
