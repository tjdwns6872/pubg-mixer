import { request } from "./axios";
import type { Member, MemberCreateRequest } from "../types/member";

const BASE_PATH = "/api/members";

export async function createMembers(payload: MemberCreateRequest) {
  return request<Member[]>({
    url: BASE_PATH,
    method: "POST",
    data: payload,
  });
}

