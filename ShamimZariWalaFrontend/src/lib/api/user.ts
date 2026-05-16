import { apiClient } from "@/lib/api/apiClient"

export const loginUser = async (data: {
  email: string;
  password: string;
}) => {
  const res = await apiClient.post("/auth/login", data);
  return res.data;
};