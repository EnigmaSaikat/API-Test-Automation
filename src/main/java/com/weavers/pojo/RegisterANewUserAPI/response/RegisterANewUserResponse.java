package com.weavers.pojo.RegisterANewUserAPI.response;

public class RegisterANewUserResponse {
    private int code;
    private String message;
    private Data data;

    // Default constructor
    public RegisterANewUserResponse() {
    }

    // Parameterized constructor
    public RegisterANewUserResponse(int code, String message, Data data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // Getters
    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Data getData() {
        return data;
    }

    // Setters
    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(Data data) {
        this.data = data;
    }

    // toString method
    @Override
    public String toString() {
        return "ApiResponse{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    // Inner class for Data
    public static class Data {
        private String id;

        // Default constructor
        public Data() {
        }

        // Parameterized constructor
        public Data(String id) {
            this.id = id;
        }

        // Getter
        public String getId() {
            return id;
        }

        // Setter
        public void setId(String id) {
            this.id = id;
        }

        // toString method
        @Override
        public String toString() {
            return "Data{" +
                    "id='" + id + '\'' +
                    '}';
        }
    }
}
