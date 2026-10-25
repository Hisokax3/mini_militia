using UnityEngine;

public class cameraFollow : MonoBehaviour
{
    public Transform target;
    public float smoothSpeed = 0.225f;
    public Vector3 offset;
    private float startingZ; 
    private Vector3 velocity = Vector3.zero;

    void Start()
    {
        startingZ = transform.position.z;
    }

    void LateUpdate()
    {
        if (target == null)
        {
            Debug.LogError("Camera follow target is not assigned!");
            return;
        }

        Vector3 desiredPosition = new Vector3(target.position.x + offset.x, target.position.y + offset.y, startingZ);
        
        Vector3 smoothedPosition = Vector3.SmoothDamp(transform.position, desiredPosition, ref velocity ,smoothSpeed);
        transform.position = smoothedPosition;
    }
}
