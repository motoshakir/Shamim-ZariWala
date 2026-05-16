import { useMutation } from "@tanstack/react-query";
import { loginUser } from "@/lib/api/user";

export const useLogin = () => {
  return useMutation({
    mutationFn: loginUser,
  });
};